import React, {useState} from 'react'
import PropTypes from 'prop-types';
import FakeSinDashboard from '../containers/FakeSinDashboard'
import {IconButton, Grid, Typography, Avatar, Button, Tabs, Tab } from '@material-ui/core';
import './Sin.css';


const ProfileHeader = (props) => {
    const defaultAvatar = require('./default_avatar.png')
    const user = props.user || ({
        username: "@Markiro420",
        about : "Love jerking and jerking and jerking\nYou should throw lsd to understand me",
        friends : [
            {name: 'Pochemu'},
            {name: 'Net'},
            {name: 'Igri'},
            {name: 'Gde'},
            {name: 'Tu'},
            {name: 'Tipo'},
            {name: 'Gribok'},
            {name: 'A'},
            {name: 'Oxxxy'},
            {name: 'Skazhi'},
            {name: 'Pochemu'},
        ]
    })
    return (
        <Grid container spacing={24}
            className="profile-header"
            direction="row"
            justify="flex-start"
            alignItems="flex-start"
        >
            <Grid item xs={2} container
                justify="center"
                alignItems="center"
            >
                <Grid item>
                    <Avatar style={{width: '160px', height: '160px'}} alt='avatar' src={defaultAvatar} />
                </Grid>
            </Grid>
            <Grid item xs={7} container
                direction="column"
                justify="flex-start"
                alignItems="flex-start"
            >
                <Grid item container direction="row" justify="flex-start" alignItems="flex-end">
                    <Grid item>
                        <p className="profile-username"> {user.username.toUpperCase()} </p>
                    </Grid>
                    <Grid item>
                        <Button>
                            FOLLOW
                        </Button>
                    </Grid>
                    <Grid item xs>
                    </Grid>
                </Grid>
                
                <Grid item>
                    {user.about.split('\n').map((line, i) =>
                        (<p className="profile-about" key={i}>{line}</p>)
                    )}
                </Grid>
            </Grid>
            <Grid item xs={3} container direction='column' justify='center' alignItems='center'>
                <Grid item>
                    {'FRIENDS ' + user.friends.length}
                </Grid>
                <Grid item xs container>
                    {
                        user.friends.slice(0, 18).map((friend, i) => (
                            <Avatar key={i} style={{margin: '4px'}}>
                                {friend.name[0]}
                            </Avatar>
                        ))
                    }
                </Grid>
            </Grid>
        </Grid>
    )
}
ProfileHeader.propTypes = {
    user : PropTypes.object
}


const Profile = (props) => {
    const [value, setValue] = useState(0)
    return (
        <div>
            <ProfileHeader />
            <Tabs
                className='profile-tabs'
                variant="fullWidth"
                value={value}
                onChange={(e, v) => setValue(v)}
            >
                <Tab
                    style= {{backgroundColor: value === 0 ? "#F5F5F5" : "#373737"}}
                    label={<span style={{ color: value === 0 ? "#373737" : '#ffffff' }}>Dashboard</span>}
                />
                <Tab
                    style= {{backgroundColor: value === 1 ? "#F5F5F5" : "#373737"}}
                    label={<span style={{ color: value === 1 ? "#373737" : '#ffffff' }}>Sin Face Outs</span>}
                />
                <Tab
                    style= {{backgroundColor: value === 2 ? "#F5F5F5" : "#373737"}}
                    label={<span style={{ color: value === 2 ? "#373737" : '#ffffff' }}>Favourites</span>}
                />
            </Tabs>
            {value === 0 && <FakeSinDashboard />}
            {value === 1 && <div>Item Two</div>}
            {value === 2 && <div>Item Three</div>}
        </div>
    )
}
Profile.propTypes = {
    user : PropTypes.object
}
export default Profile