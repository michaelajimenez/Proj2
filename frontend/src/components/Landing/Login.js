import React, { useState, useEffect } from "react";
import { Form, FormInput, FormGroup } from "shards-react";
import Axios from "axios";
import { useHistory } from "react-router-dom";
import { updateStatus } from "../../redux/actions/loggedInAction";
import { updateUser } from "../../redux/actions/userAction";
import { useDispatch } from "react-redux";

export default function Login({ togglePanel }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isError, setIsError] = useState(false);

  const dispatch = useDispatch();

  const history = useHistory();

  const clearForm = () => {
    setPassword("");
    setEmail("");
  };

  const submitForm = (e) => {
    e.preventDefault();
    Axios.post("http://localhost:8080/ProjectTwo/earthlings/api/login", {
      username: email,
      password: password,
    })
      .then((res) => {
        clearForm();
        if (res.data != "") {
          dispatch(updateStatus(true));

          dispatch(updateUser(res.data));

          return res.data;
        } else {
          // Add something to tell people the login did not work
          dispatch(updateStatus(false));
          console.log("ope");
        }
      })
      .then((data) => {
        history.push({
          pathname: "/feed",
          state: { id: data.userId },
        });
      });
  };
  useEffect(() => {
    clearForm();
  }, [togglePanel]);
  return (
    <div className="form-container sign-in-container">
      <Form
        onSubmit={(e) => {
          submitForm(e);
        }}>
        <h1 className="form-title">Sign In</h1>
        <FormGroup>
          <label htmlFor="#username">Email</label>
          <FormInput
            type="text"
            id="#username"
            placeholder="Username"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </FormGroup>
        <FormGroup>
          <label htmlFor="#password">Password</label>
          <FormInput
            type="password"
            id="#password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </FormGroup>
        <button onClick={(e) => submitForm(e)} className="form-button">
          Sign In
        </button>
        <a className="find-password">Forgot Password?</a>
      </Form>
    </div>
  );
}
