import React, { useState, useEffect } from "react";
import Axios from "axios";
import { Form, FormInput, FormGroup, Modal, ModalBody } from "shards-react";
import addPhoto from "../../photos";

export default function SignUp({ togglePanel }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [fullName, setFullname] = useState("");
  const [profilePic, setProfilepic] = useState();
  const [modalActive, setModalActive] = useState(false);
  const [modalMessage, setModalMessage] = useState("👋");

  const clearForm = () => {
    setPassword("");
    setEmail("");
    setUsername("");
    setFullname("");
    setProfilepic();
  };

  const uploadPhoto = (photo) => {
    setProfilepic(
      `https://earthlings-bucket.s3.amazonaws.com/${addPhoto("Album", photo)}`
    );
  };

  const createuser = () => {
    console.log("in axios call");
    Axios.post("http://localhost:8080/ProjectTwo/earthlings/api/createUser", {
      username: username,
      password: password,
      email: email,
      fullName: fullName,
      created: new Date(),
      imageUrl: profilePic,
    }).then((res) => {
      clearForm();
      setModalMessage("Account created! Sign in to your account now!");
      setModalActive(true);
    });
  };

  const submitForm = () => {
    createuser();
  };

  useEffect(() => {
    clearForm();
  }, [togglePanel]);

  return (
    <div className="form-container sign-up-container">
      <Form
        onSubmit={(e) => {
          e.preventDefault();
          submitForm();
        }}>
        <h1 className="form-title">Sign Up Today!</h1>
        <FormGroup>
          <label htmlFor="#username">Make a username</label>
          <FormInput
            id="#username"
            placeholder="Username"
            onChange={(e) => setUsername(e.target.value)}
          />
        </FormGroup>
        <FormGroup>
          <label htmlFor="#fullname">Enter full name</label>
          <FormInput
            id="#fullname"
            placeholder="fullname"
            onChange={(e) => setFullname(e.target.value)}
          />
        </FormGroup>
        <FormGroup>
          <label htmlFor="#email">Enter an email</label>
          <FormInput
            id="#email"
            type="email"
            placeholder="ex: johnsmith@gmail.com"
            onChange={(e) => setEmail(e.target.value)}
          />
        </FormGroup>
        <FormGroup>
          <label htmlFor="#password">Create a password</label>
          <FormInput
            id="#password"
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
        </FormGroup>
        <FormGroup>
          <label htmlFor="#file">Upload an image (Optional)</label>
          <FormInput
            id="#file"
            type="file"
            onChange={(e) => uploadPhoto(e.target.files[0])}
          />
        </FormGroup>
        <button className="form-button">Create Account</button>
      </Form>
      <Modal size="lg" open={modalActive} toggle={() => setModalActive(false)}>
        <ModalBody> {modalMessage}</ModalBody>
      </Modal>
    </div>
  );
}
