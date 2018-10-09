import React, { Component } from 'react'
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import RNBridge from './CallNative'

type Props = {};
export default class SplashScreen extends React.Component<Props> {
// static navigationOptions = {
//         title: 'react-native first page',
//
//         headerText:{
//             textAlign: 'center',
//             fontWeight: 'bold',
//         },
//     };

     componentWillMount() {
           RNBridge.callNative("yes")
         }
  render() {
  const { navigate } = this.props.navigation;
    return (
      <View style={styles.container}>
        <Text style={styles.hello} onPress={()=>navigate('ProfileScreen',{message:"come here"})}>go to next</Text>
      </View>

    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  hello: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  }
});
