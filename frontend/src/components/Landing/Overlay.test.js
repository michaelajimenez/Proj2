import React from 'react';
import renderer from 'react-test-renderer';
import ReactDOM from "react-dom";
import Overlay from './Overlay.js';
import Enzyme, { shallow } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });


test('Overlay Component renders properly',()=>{
    const div = document.createElement("div");
    ReactDOM.render(<Overlay/>,div)
});

test('component rerenders when props change',()=>{
    const component = renderer.create(<Overlay/>);
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();

    tree.props.togglePanel = true;
    tree = component.toJSON();
    expect(tree).toMatchSnapshot();
    
    tree.props.togglePanel = false;
    tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

test('Overlay Component contains One div named overlay-container', ()=>{
    let component = shallow(<Overlay/>);
    expect(component.find("div.overlay-container")).toHaveLength(1);
});