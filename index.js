import React from "react";
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import SplashScreen from './splash'
import ProfileScreen from './profile'
import {
  StackNavigator,
} from 'react-navigation';
//
const App = StackNavigator({
  SplashScreen: {screen: SplashScreen},
  ProfileScreen: {screen: ProfileScreen},
});


AppRegistry.registerComponent('android', () => App);



