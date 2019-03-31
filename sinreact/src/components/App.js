import React from 'react'
import './App.css'
import FakeSinDashboard from '../containers/FakeSinDashboard'
import Error from './ErrorView'
import UserLogin from './UserLogin'
import Header from './Header'
import {Route, Switch} from 'react-router-dom'
import BibleView from './BibleView'
import Profile from './Profile'


const App = () => (
    <div>
        <Header/>
        <Switch>
            <Route exact path="/login" component={UserLogin}/>
            <Route path="/dashboard" component={FakeSinDashboard}/>
            <Route path="/bible" component={BibleView}/>
            <Route path="/profile" component={Profile}/>
            <Route path="/" component={Error}/>
        </Switch>
    </div>
)

export default App;
