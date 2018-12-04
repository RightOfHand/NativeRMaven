import React, { Component } from 'react'
import { AppRegistry,
 StyleSheet,
 Text,
 View,
 Alert,
 ToastAndroid } from "react-native";
import RNBridge from './CallNative'
import CodePush from 'react-native-code-push';

export default class SplashScreen extends React.Component{
  constructor(props) {
    super(props);
  }
  static navigationOptions = {
         title: 'code push',

         headerText:{
             textAlign: 'center',
             fontWeight: 'bold',
         },
  };
  

     componentDidMount() {
    };
     componentDidMount(){
//                let data =  CodePush.sync({
//                    updateDialog: {
//                        appendReleaseDescription:true,
//                        descriptionPrefix:'更新内容:',
//                        mandatoryContinueButtonLabel:'更新',
//                        mandatoryUpdateMessage:'有新版本了，请您及时更新',
//                        optionalInstallButtonLabel: '立即更新',
//                        optionalIgnoreButtonLabel: '稍后',
//                        optionalUpdateMessage:'有新版本了，是否更新？',
//                        title: '提示'
//                    },
//                    installMode: CodePush.InstallMode.IMMEDIATE
//                });
     };

    _callDebug(){
      ToastAndroid.show(this.props.curEnv,ToastAndroid.LONG);
          RNBridge.debug(this.props.curEnv,(result) => {

                  })
        };
    _hotUpdate = ()=>{
            console.log("CodePush-up",CodePush);


        };
  render() {
  const { params } = this.props.navigation.state;
  //navigate('ProfileScreen',{message:"come here"})
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>native initialProps is {JSON.stringify(this.props.screenProps)}</Text>
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
