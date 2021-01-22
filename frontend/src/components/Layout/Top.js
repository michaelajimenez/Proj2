import React from "react";

import { useDispatch, useSelector } from "react-redux";
import { Layout } from "antd";
const { Header } = Layout;

export default function Top() {
  return (
    <Header className="navBar">
      <h1 className="logo">Earthlings</h1>
    </Header>
  );
}
