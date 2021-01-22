import React, { useState } from "react";
import Axios from "axios";
import { useSelector } from "react-redux";
import { Layout, Button, Input } from "antd";
import addPhoto from "../../photos";

const { Sider } = Layout;
const { TextArea } = Input;

export default function RightSide() {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [pic, setPic] = useState();
  const user = useSelector((state) => state.setUser);

  const uploadPhoto = (photo) => {
    setPic(
      `https://earthlings-bucket.s3.amazonaws.com/${addPhoto("Album", photo)}`
    );
  };

  const clearForm = () => {
    setTitle("");
    setBody("");
    setPic("");
  };

  const createPost = () => {
    if (title != "") {
      Axios.post("http://localhost:8080/ProjectTwo/earthlings/api/createPost", {
        title: title,
        body: body,
        createdAt: new Date(),
        postImageUrl: pic,
        user: user,
      });
      clearForm();
    }
  };

  return (
    <Sider
      style={{
        overflow: "auto",
        height: "100vh",
        position: "fixed",
        right: 0,
      }}
      className="posty">
      <Input
        style={{ marginBottom: "1.5em" }}
        placeholder={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <TextArea
        showCount
        maxLength={250}
        onChange={(e) => setBody(e.target.value)}
        style={{ marginBottom: "1em" }}
        placeholder={body}
      />
      <Input
        type="file"
        onChange={(e) => uploadPhoto(e.target.files[0])}
        style={{ marginBottom: "1em" }}
        placeholder={pic}
      />
      <Button
        block
        onClick={() => createPost()}
        style={{
          border: "none",
          background: "rgba(1,1,60,1)",
          color: "#333",
        }}>
        Create Post
      </Button>
    </Sider>
  );
}
