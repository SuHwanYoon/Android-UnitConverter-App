package yoon.tutorials.androidapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yoon.tutorials.androidapp.ui.theme.AndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //UI함수 호출
                    UnitConvert()
                }
            }
        }
    }
}

@Composable
fun UnitConvert() {

    //String입력값
    var inputValue by remember { mutableStateOf("") }
    //String출력값
    var outputValue by remember { mutableStateOf("") }
    //입력단위
    var inputUnit by remember { mutableStateOf("Centimeters") }
    //출력단위
    var outputUnit by remember { mutableStateOf("Meters") }
    //입력확장
    var inputExpanded by remember { mutableStateOf(false) }
    //출력확장
    var outputExpanded by remember { mutableStateOf(false) }
    //변환하는데 사용할 변수
    val conversionFactor = remember { mutableDoubleStateOf(0.01) }
    //UI요소를 세로로 쌓는 컬럼 함수
    Column(
        //UI가 화면을 가득채우도록
        modifier = Modifier.fillMaxSize(),
        //UI 세로 중앙에 위치
        verticalArrangement = Arrangement.Center,
        //UI default start -> center
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //text element
        Text(text = "Yoon's Unit Converter")
        //spcer+ dencity Pixel로 일관된 간격을 설정하기
        Spacer(modifier = Modifier.height(16.dp))
        //테두리를 가진 텍스트 입력필드UI
        //필드에 표시될 텍스트값, 텍스트 필드의 값이 변경될 때 호출되는 콜백함수를 파라미터로 가짐
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                //사용자 입력 텍스트 it이  새로운 텍스트값(inputValue)으로 업데이트
                //inputValue값이 value 속성에 반영되어 디스플레이 됨
                inputValue = it
            },
            //placeHolder
            label = { Text(text = "Please Enter Value!") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //drowdown menu를 만들기 위해 box사용
            //input box
            Box {
                //input button
                //버튼을 눌렀을때 inputExpanded 변수값을 true로 변경
                Button(onClick = { inputExpanded = true }) {
                    Text(text = "DropDown1")
                    //button의 type, 접근성
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                //dropdown menu setting
                //dropdown 확장 기본값 false 지정 , dropdown menu가 닫혔을때 실행될 함수
                //드롭다운 1
                //확장의 상태를 결정하는 inputExpanded변수 , 사용자가 dropdown을 해지하려했을때의요청(onDismissRequest)
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                    //dropdown menu List
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("M") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "M"
                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("cm") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "cm"
                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("mm") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "mm"
                        }
                    )
                }
            }
            //두 버튼 사이 간격 주기
            //android interface는 주로 8, 16, 32 dp를 사용
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                //버튼을 눌렀을때 outputExpanded의 값을 true로 변경
                Button(onClick = { outputExpanded = true }) {
                    Text(text = "DropDown2")
                    //button의 type을 dropdown으로설정, 접근성
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                //드롭다운 2
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                    //dropdown menu List
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("M") },
                        onClick = {
                            //dropdown 확장 메뉴를 클릭했을때
                            outputExpanded = false
                            outputUnit = "M"

                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("cm") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "cm"

                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("mm") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "mm"
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConvertPreview() {
    //Composable + Preview(suffix)
    UnitConvert()
}