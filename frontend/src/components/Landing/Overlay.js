import React from "react";
export default function Overlay({ togglePanel }) {
  return (
    <div className="overlay-container">
      <div className="overlay">
        <div className="overlay-panel overlay-left">
          <h1>Have an Account?</h1>
          <p className="overlay-description">
            How about signing back in to your current?
          </p>
          <button
            className="overlayBtn"
            onClick={() => {
              togglePanel(false);
            }}>
            Sign In
          </button>
        </div>
        <div className="overlay-panel overlay-right">
          <h1>Need an Account?</h1>
          <p className="overlay-description">Click below to sign up today!</p>

          <button
            className="overlayBtn"
            onClick={() => {
              togglePanel(true);
            }}>
            Sign Up
          </button>
        </div>
      </div>
    </div>
  );
}
