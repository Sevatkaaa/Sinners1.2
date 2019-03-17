import React from 'react'
import SinCard from './SinCard'
import Grid from '@material-ui/core/Grid';


class SinsDashboard extends React.Component {
	constructor(props) {
		super(props)
		var counter = 0;
		const makeSin = (username, type, points, text) => ({id: counter++, username: username, sinType: type, points: points, sinText: text})
		this.state = {
			sins: [
				makeSin("Seva", "Drocha", 50, "Бросил Дрочить"),
				makeSin("Markiro", "Drocha", 10, "Podrochiv"),
				makeSin("Markiro", "Drocha", 15, "Podrochiv Levoy"),
				makeSin("Seva", "Drocha", 30, "Подрочить на Зарубежке"),
				makeSin("Seva", "Drocha", 5, "Konchiv"),
				makeSin("Markiro", "Drocha", 25, "Podrochiv bez ruk"),
				makeSin("Markiro", "Drocha", 15, "Podrochiv Seve"),
				makeSin("Seva", "Drocha", 29, "Подрочить на географии. Подрочить на географии. Подрочить на географии. Подрочить на географии. Подрочить на географии"),
			],
			score: 0
		}
	}
	
	render = () => (
		<div>
			<Grid container>
				<Grid container className="sins-dashboard">
					<Grid item>
						<p style={{fontSize:"40px", color:"#ffffff", verticalAlign:"center"}}><b>{"My "}</b> SINS <b>{" Dashboard"}</b></p>
					</Grid>
					<Grid xs/>
				</Grid>
				<Grid container justify="center">
					{this.state.sins.map((sin, i) => (
						<SinCard
							sin={sin}
							onClick={() => this.setState({score: this.state.score + sin.points})}
						/>
					))}
				</Grid>
			</Grid>
		</div>
	)
}

export default SinsDashboard;