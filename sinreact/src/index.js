import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'
import './index.css'
import App from './components/App'
import UserLogin from './components/UserLogin'
import FakeSinDashboard from './containers/FakeSinDashboard'
import * as serviceWorker from './serviceWorker'
import { store } from './store/store'
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core'
import { Route, Router, BrowserRouter } from 'react-router-dom'


const theme = createMuiTheme({
    palette: {
        type: 'dark',
        primary: {
            main: '#373737'
        },
        secondary: {
            main: '#F5F5F5'
        },
    }
});

ReactDOM.render(
    <MuiThemeProvider theme={theme}>
        <Provider store={store}>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </Provider>
    </MuiThemeProvider>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
