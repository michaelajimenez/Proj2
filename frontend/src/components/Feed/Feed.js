import React from "react";
import { Container, Row, Col } from "shards-react";
import Posts from "./Posts";

export default function Feed() {
  return (
    <Container>
      <Row>
        <Col sm="12" md="4" lg="6">
          <Posts />
        </Col>
        <Col sm="12" md="4" lg="3"></Col>
      </Row>
    </Container>
  );
}
