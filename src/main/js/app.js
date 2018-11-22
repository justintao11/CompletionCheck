'use strict'

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    componentDidMount() {
        console.log("in APP")

    }

    render() {
        return (
                <h1>REACT TEST</h1>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)