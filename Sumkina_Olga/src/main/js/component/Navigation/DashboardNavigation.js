import React from 'react'
import {Link} from 'react-router-dom'


export default class DashboardNavigation extends React.Component {
    render() {
        return (
            <div className="wrapper-navigation">
                <ul>
                    <li className="navigation-item"><Link to="/dashboard/profile">Profile</Link></li>
                    <li className="navigation-item"><Link to="/dashboard/service">Service</Link></li>
                    <li className="navigation-item"><Link to="/dashboard/employee">Employee</Link></li>
                </ul>
            </div>
        )
    }
}

