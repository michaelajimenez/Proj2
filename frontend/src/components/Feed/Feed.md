# WIP

import Axios from "axios";
import Post from "./components/Feed/Post.js";

const [posts, setPosts] = useState([]);
useEffect(() => {
Axios.get("https://jsonplaceholder.typicode.com/posts").then((res) =>
setPosts(res.data)
);
}, []);
