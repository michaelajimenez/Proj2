import React from "react";
import Header from "../components/Layout/Top";
import Feed from "../components/Feed/Feed";
import { Row, Col } from "antd";

export default function FeedPage() {
  return (
    <Row>
      <Col span={12} offset={5}>
        <Feed />
      </Col>
    </Row>
  );
}
