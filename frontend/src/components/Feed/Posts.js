import React, { createElement, useState, useEffect } from "react";
import { Card, Avatar, Tooltip, Skeleton } from "antd";
import { Link } from "react-router-dom";
import Axios from "axios";
import Post from "./Post";

const { Meta } = Card;

export default function Posts() {
  const [content, setContent] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    Axios.get(
      `http://localhost:8080/ProjectTwo/earthlings/api/getAllUsers`
    ).then((res) => {
      setIsLoading(false);
      setContent(
        res.data.filter((userWithPosts) => {
          if (userWithPosts.posts.length > 0) {
            return userWithPosts;
          }
        })
      );
    });
  }, []);
  return (
    <>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      {console.log(content)}
      {content.map((user) =>
        user.posts.map((post) => {
          return <Post key={post.postId} user={user} post={post} />;
        })
      )}
    </>
  );
}
