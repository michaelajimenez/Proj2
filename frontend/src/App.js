import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import LandingPage from "./Pages/LandingPage.jsx";
import ProfilePage from "./Pages/ProfilePage.jsx";
import SearchPage from "./Pages/SearchPage.jsx";
import FeedPage from "./Pages/FeedPage.jsx";
import { useDispatch, useSelector } from "react-redux";
import { updateStatus } from "./redux/actions/loggedInAction";
import { Layout } from "antd";

import Top from "./components/Layout/Top";
import Side from "./components/Layout/Side";
import RightSide from "./components/Layout/RightSide.js";

const { Content } = Layout;

export default function App() {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(updateStatus(false));
  }, []);
  const loggedIn = useSelector((state) => state.isLogged);
  return (
    <>
      <Router>
        <Layout>
          {loggedIn == true && <Top />}
          <Layout>
            {loggedIn == true && <Side />}
            <Content>
              <Switch>
                <Route path="/profile">
                  <ProfilePage />
                </Route>
                <Route path="/feed">
                  <FeedPage />
                </Route>
                <Route path="/search">
                  <SearchPage />
                </Route>

                <Route path="/">
                  <LandingPage />
                </Route>
              </Switch>
            </Content>
            {loggedIn == true && <RightSide />}
          </Layout>
        </Layout>
      </Router>
    </>
  );
}
/*
  <Layout>
        <Sider>Sider</Sider>
        <Layout>
          <Header>Header</Header>
          <Content>
            <Router>
              <Switch>
                <Route path="/ear">
                  <ProfilePage />
                </Route>
                <Route path="/sdf">
                  <FeedPage />
                </Route>
                <Route path="/rch">
                  <SearchPage />
                </Route>

                <Route path="/">
                  <LandingPage />
                </Route>
              </Switch>
            </Router>
          </Content>
        </Layout>
      </Layout>
      */
