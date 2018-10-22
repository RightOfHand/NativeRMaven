import React, { Component } from 'react'
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import RNBridge from './CallNative'

type Props = {};
export default class SplashScreen extends React.Component<Props> {
 static navigationOptions = {
         title: 'code-push',

         headerText:{
             textAlign: 'center',
             fontWeight: 'bold',
         },
     };


     componentWillMount() {

         }
    _callNativeModule(){
      RNBridge.send("yes",(result) => {

              })
    }
  render() {
  const { navigate } = this.props.navigation;
  //navigate('ProfileScreen',{message:"come here"})
    return (
      <View style={styles.container}>
        <Text style={styles.hello} onPress={()=>this._callNativeModule()}>go to next</Text>
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
