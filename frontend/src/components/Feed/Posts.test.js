import React from 'react';
import renderer from 'react-test-renderer';
import ReactDOM from "react-dom";
import Posts from './Posts.js';
import Enzyme, { shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });


test('Posts Plural Component renders properly',()=>{
    const div = document.createElement("div");
    ReactDOM.render(<Posts/>,div)
});


test('Posts Plural Component contains Five Skeleton Element', ()=>{
    let component = shallow(<Posts/>);
    expect(component.find("Skeleton")).toHaveLength(5);
});
