import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";

import { updateStatus } from "../../redux/actions/loggedInAction";
import Axios from "axios";
import { Layout, Avatar, Modal, Button } from "antd";
import {
  HomeOutlined,
  SearchOutlined,
  MessageOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Form, FormInput, FormGroup, Nav, NavItem } from "shards-react";
import { Link, useHistory } from "react-router-dom";
import addPhoto from "../../photos";
const { Sider } = Layout;

export default function Side() {
  const user = useSelector((state) => state.setUser);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [fullName, setFullname] = useState("");
  const [profilePic, setProfilepic] = useState("");
  const [isModalVisible, setIsModalVisible] = useState(false);

  const history = useHistory();

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
    console.table({ username, password, email, fullName, profilePic });
    Axios.put("http://localhost:8080/ProjectTwo/earthlings/api/editUser", {
      userId: user.userId,
      username: username,
      password: password,
      email: email,
      fullName: fullName,
      imageUrl: profilePic,
    }).then((res) => {
      clearForm();
    });
  };
  const dispatch = useDispatch();

  const submitForm = () => {
    createuser();
  };

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <>
      <Sider
        style={{
          overflow: "auto",
          height: "100vh",
          position: "fixed",
          left: 0,
        }}
        className="leftSide">
        <div style={{ textAlign: "center" }}>
          <Avatar size={64} icon={<UserOutlined />} src={user.imageUrl} />
          <p>{user.fullName}</p>
          <p>{user.username}</p>
        </div>
        <Nav>
          <NavItem>
            <Link
              to={{
                pathname: "/profile",
                state: { id: user.userId },
              }}>
              <HomeOutlined />
              <span className="spacer">Profile</span>
            </Link>
          </NavItem>
          <NavItem>
            <Link to="/feed">
              <MessageOutlined />
              <span className="spacer">Feed</span>
            </Link>
          </NavItem>
          <NavItem>
            <Link to="/search">
              <SearchOutlined />
              <span className="spacer">Search</span>
            </Link>
          </NavItem>
          <NavItem>
            <Link
              onClick={() => {
                dispatch(updateStatus(false));
              }}
              to="/">
              Log Out
            </Link>
          </NavItem>
        </Nav>

        <button onClick={() => showModal(true)}>Edit account</button>
      </Sider>
      <Button type="primary" onClick={showModal}>
        Open Modal
      </Button>
      <Modal
        title="Basic Modal"
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}>
        <Form
          onSubmit={(e) => {
            e.preventDefault();
            submitForm();
          }}>
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
      </Modal>
    </>
  );
}

const App = () => {
  return <></>;
};
