import React from "react";
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import RNBridge from './CallNative'

class HelloWorld extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.hello} onPress={()=>RNBridge.callNative("原生跳用")}>maven react native Hello, World</Text>
      </View>
    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center"
  },
  hello: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  }
});

AppRegistry.registerComponent("android", () => HelloWorld);