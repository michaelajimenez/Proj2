import React, { useState, useEffect } from "react";
import { Row, Col, List, Avatar, Skeleton } from "antd";
import { Link } from "react-router-dom";
import Axios from "axios";

export default function SearchPage() {
  const [userSearch, setUser] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    Axios.get(
      `http://localhost:8080/ProjectTwo/earthlings/api/getAllUsers`
    ).then((res) => {
      setIsLoading(false);
      setUser(res.data); /*(posts = res.data)*/
    });
  }, []);
  return (
    <>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Skeleton loading={isLoading} active avatar></Skeleton>
      <Row>
        <Col span={12} offset={5}>
          profile
        </Col>
      </Row>
      <Row>
        <Col span={12} offset={5}>
          <List
            itemLayout="horizontal"
            dataSource={userSearch}
            renderItem={(item) => (
              <List.Item>
                <List.Item.Meta
                  avatar={
                    <Avatar
                      src={
                        item.imageUrl != ""
                          ? item.imageUrl
                          : "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                      }
                    />
                  }
                  title={
                    <Link
                      to={{
                        pathname: "/profile",
                        state: { id: item.userId },
                      }}>
                      {item.fullName}
                    </Link>
                  }
                  description={item.username}
                />
              </List.Item>
            )}
          />
        </Col>
      </Row>
    </>
  );
}
