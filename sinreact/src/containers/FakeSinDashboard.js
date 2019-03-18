import React from 'react'
import { connect } from 'react-redux'
import { addFakeSinCards } from '../store/actions'
import SinCard from '../components/SinCard'
import { IconButton, Grid } from '@material-ui/core';
import { Schedule } from '@material-ui/icons';
import { store } from '../store/store'

var counter = 0
const makeSin = (username, type, points, text) => ({ id: counter++, username: username, sinType: type, points: points, sinText: text })

const FakeSinDashboard = (props) => {
	const sins = props.fakeSins || []
	return (
		<div>
			<Grid container>
				<Grid container className="sins-dashboard">
					<Grid item>
						<p style={{ fontSize: "40px", color: "#ffffff", verticalAlign: "center" }}><b>{"My "}</b> SINS <b>{" Dashboard"}</b></p>
					</Grid>
					<Grid item xs />
					<Grid item>
						<Grid container alignItems="center" justify="center" direction="column">
							<IconButton onClick={() => {
								store.dispatch(addFakeSinCards([makeSin("Mark", "Drocha", 22, "Hello from redux")]))
							}}>
								<Schedule />
							</IconButton>
						</Grid>
					</Grid>
				</Grid>
				<Grid container justify="center">
					{sins.map((sin) => (<SinCard key={sin.id} sin={sin} />))}
				</Grid>
			</Grid>
		</div>
	)
}

const mapStateToProps = (state) => ({
	fakeSins: state.fakeSins
})

export default connect(
	mapStateToProps
)(FakeSinDashboard)