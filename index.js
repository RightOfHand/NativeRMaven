import React from "react";
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import SplashScreen from './splash'
import ProfileScreen from './profile'
import {
  StackNavigator,
  createNavigator
} from 'react-navigation';
//

var routeConfigs ={
  SplashScreen: { screen: SplashScreen},
  ProfileScreen: {screen: ProfileScreen},
}
var stackNavigatorConfig ={
  initialRouteName :'SplashScreen',
  initialRouteParams :{curEnr:'curEnv'}
}
const App = StackNavigator({
  SplashScreen: { screen: SplashScreen},
  ProfileScreen: {screen: ProfileScreen},
});

class GlobalConfig extends React.Component{
  render(){
    return <App screenProps={this.props}/>
  }
}

AppRegistry.registerComponent('android', () => GlobalConfig);



