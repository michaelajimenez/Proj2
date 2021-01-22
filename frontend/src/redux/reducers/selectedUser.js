const initialState = {};

export default (state = initialState, { type, payload }) => {
  switch (type) {
    case "SET_PROFILE_DATA":
      return payload;

    default:
      return state;
  }
};
