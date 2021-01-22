const loggedInReducer = (state = false, { type, payload }) => {
  switch (type) {
    case "UPDATE_STATUS":
      return payload;
    default:
      return state;
  }
};

export default loggedInReducer;
