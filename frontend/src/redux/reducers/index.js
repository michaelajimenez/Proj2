import { combineReducers } from "redux";
import loggedInReducer from "./loggedIn";
import setUser from "./userReducer";
import setViewer from "./selectedUser";

export default combineReducers({
  isLogged: loggedInReducer,
  setUser: setUser,
  setProfile: setViewer,
});
