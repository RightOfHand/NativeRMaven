import React, { Component } from 'react'
import { AppRegistry, StyleSheet, Text, View,Alert } from "react-native";
import RNBridge from './CallNative'
import CodePush from 'react-native-code-push';

type Props = {};
export default class SplashScreen extends React.Component<Props> {
 static navigationOptions = {
         title: 'code-push',

         headerText:{
             textAlign: 'center',
             fontWeight: 'bold',
         },
     };


//     componentDidMount() {
//      CodePush.getCurrentPackage()
//	  .then( (info) =>{
//		Alert.alert(info);
//	   })
//         }
     componentDidMount(){
                let data =  CodePush.sync({
                    updateDialog: {
                        appendReleaseDescription:true,
                        descriptionPrefix:'更新内容:',
                        mandatoryContinueButtonLabel:'更新',
                        mandatoryUpdateMessage:'有新版本了，请您及时更新',
                        optionalInstallButtonLabel: '立即更新',
                        optionalIgnoreButtonLabel: '稍后',
                        optionalUpdateMessage:'有新版本了，是否更新？',
                        title: '提示'
                    },
                    installMode: CodePush.InstallMode.IMMEDIATE
                });
     }
    _callNativeModule(){
      RNBridge.send("yes",(result) => {

              })
    }
    _hotUpdate = ()=>{
            console.log("CodePush-up",CodePush);


        };
  render() {
  const { navigate } = this.props.navigation;
  //navigate('ProfileScreen',{message:"come here"})
    return (
      <View style={styles.container}>
        <Text style={styles.hello} onPress={()=>this._hotUpdate}>current version 1.2.2</Text>
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
