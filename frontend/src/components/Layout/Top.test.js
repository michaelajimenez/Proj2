import React from 'react';
import renderer from 'react-test-renderer';
import ReactDOM from "react-dom";
import Top from './Top.js';
import Enzyme, { shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });


test('Top Component renders properly',()=>{
    const div = document.createElement("div");
    ReactDOM.render(<Top/>,div)
});


test('Top Component contains One Header named navBar', ()=>{
    let component = shallow(<Top/>);
    expect(component.find("Header.navBar")).toHaveLength(1);
});