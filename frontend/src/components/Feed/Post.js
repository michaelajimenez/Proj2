import React, { useState, useEffect } from "react";
import { Card, Avatar, Tooltip, Skeleton } from "antd";
import { LikeTwoTone } from "@ant-design/icons";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

import Axios from "axios";

const { Meta } = Card;

export default function Post({ user, post }) {
  const likeUser = useSelector((state) => state.setUser);
  const [likes, setLikes] = useState(post.likes.length);

  const likePost = () => {
    setLikes(likes + 1);
    Axios.get("http://localhost:8080/ProjectTwo/earthlings/api/createLike", {
      userliked: likeUser,
      post: post,
    }).then(() => {});
  };

  return (
    <Card style={{ width: "100%" }}>
      <Link
        to={{
          pathname: "/profile",
          state: { id: user.userId },
        }}>
        <Meta
          avatar={<Avatar src={user.imageUrl} />}
          style={{ fontSize: "1em" }}
          title={user.fullName}
          description={user.username}
        />
      </Link>
      <p style={{ marginTop: "1em" }}>{post.title}</p>
      <p style={{ marginTop: "-1em", marginBottom: "0" }}>{post.body}</p>
      {post.postImageUrl === "" ? null : (
        <div className="imgHolder">
          <img src={post.postImageUrl} />
        </div>
      )}
      <LikeTwoTone
        twoToneColor="#eb2f96"
        onClick={() => {
          likePost();
        }}
      />
      {likes}
    </Card>
  );
}
