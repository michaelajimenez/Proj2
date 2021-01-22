import React from 'react';
import renderer from 'react-test-renderer';
import ReactDOM from "react-dom";
import Feed from './Feed.js';
import Enzyme, { shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });

test('Feed Component renders properly',()=>{
    const div = document.createElement("div");
    ReactDOM.render(<Feed/>,div);
});

test('Feed Components contains One Container Element', ()=>{
    let component = shallow(<Feed/>);
    expect(component.find("Container")).toHaveLength(1);
})