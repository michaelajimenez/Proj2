import React, { useState } from "react";
import Login from "../components/Landing/Login";
import Overlay from "../components/Landing/Overlay";
import SignUp from "../components/Landing/SignUp";
import { useDispatch, useSelector } from "react-redux";
import { Redirect } from "react-router-dom";

/* 
TODO
- Make a hook for file upload
- Make a function to log data
*/

export default function LandingPage() {
  const loggedin = useSelector((state) => state.isLogged);
  const [rightPanelActive, setRightPanelActive] = useState(false);

  return (
    <div
      className={`crontainer ${rightPanelActive ? `right-panel-active` : ``}`}
      id="container">
      <SignUp togglePanel={rightPanelActive} />
      <Login togglePanel={!rightPanelActive} />
      <Overlay togglePanel={setRightPanelActive} />
    </div>
  );
}
