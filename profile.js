import React, { Component } from 'react'
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import RNBridge from './CallNative'

export default class ProfileScreen extends React.Component {
 static navigationOptions = ({navigation}) => ({
        // 展示数据 "`" 不是单引号
        title: `react title`,

    });
    componentWillMount() {
               RNBridge.send("no")
             }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello} >maven react native Hello, World</Text>
      </View>

    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1
  },
  hello: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  }
});
