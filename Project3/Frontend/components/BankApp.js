import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

const style = {
	table: {
		display: 'block',
		width: 300
	},
	header : {
		display: 'flex'
	},
	tableRow : {
		display: 'flex',
		flexWrap: 'wrap'
	},
	value : {
		flexGrow: 1,
		width: 150
	},
	row : {
		display: 'flex',
		flexDirection: 'row'
	}
}

class BankApp extends Component{
	constructor (props){
		super(props);
		this.state = {
			userName: "Test",
			userData: null
		}
	}
	
	componentWillMount() {
		axios.get('http://localhost:8080/mvc/accounts/'+this.state.userName)
			.then((response) => this.setState({ userData: response.data }))
			.catch((errror) => console.error(errror))
	}
	
	render(){
		if (!this.state.userData) {
			return (
				<div>
				  Loading...
				</div>
			)
		}
		
		return (
			<div style={style.table}>
				<div style={style.row}>
					<div style={style.value}>Accounts of </div>
					<div style={style.value}>{this.state.userName}</div>
				</div>
				<hr/>
				<div style={style.header}>
					<div style={style.value}>Saldo</div>
					<div style={style.value}>Account Number</div>
				</div>
				{this.state.userData.map(({ id, saldo, accNum, client}) => (
					<div key={id} style={style.tableRow}>
						<div style={style.value}>{saldo}</div>
						<div style={style.value}>{accNum}</div>
					</div>
				))}
			</div>
		)
	}
}

export default BankApp;