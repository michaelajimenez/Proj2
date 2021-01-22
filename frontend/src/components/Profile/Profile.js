import React, { useState, useEffect } from "react";
import Axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { useLocation, Link } from "react-router-dom";
import { Row, Col, Card, Avatar } from "antd";
import { viewSelectedUser } from "../../redux/actions/selectUserAction";

const { Meta } = Card;

export default function Profile() {
  const dispatch = useDispatch();
  const location = useLocation();
  const userid = location.state.id;
  const loggedinUser = useSelector((state) => state.setUser);
  const viewedUser = useSelector((state) => state.setProfile);

  // const [viewing, setViewingUser] = useState({});

  useEffect(() => {
    Axios.post(
      `http://localhost:8080/ProjectTwo/earthlings/api/getUserById?userId=${userid}`
    ).then((res) => {
      dispatch(viewSelectedUser(res.data));
    });
  }, []);
  return (
    <>
      <Row>
        <Col span={10} push={6}>
          {viewedUser.posts == undefined
            ? null
            : viewedUser.posts.map((post) => {
                return (
                  <Card key={post.postId} style={{ width: "100%" }}>
                    <Link
                      to={{
                        pathname: "/profile",
                        state: { id: viewedUser.userId },
                      }}>
                      <Meta
                        avatar={<Avatar src={viewedUser.imageUrl} />}
                        style={{ fontSize: "1em", color: "#333" }}
                        title={viewedUser.fullName}
                        description={viewedUser.username}
                      />
                    </Link>
                    <p className="blk">{post.title}</p>
                    <p className="blk">{post.body}</p>

                    <img style={{ width: "100%" }} src={post.postImageUrl} />
                  </Card>
                );
              })}
          {console.log(viewedUser.posts)}
        </Col>
      </Row>
    </>
  );
}
