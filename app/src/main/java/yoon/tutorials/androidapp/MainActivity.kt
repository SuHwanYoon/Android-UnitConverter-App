package yoon.tutorials.androidapp

import android.os.Bundle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yoon.tutorials.androidapp.ui.theme.AndroidAppTheme
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

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
    //입력단위 초기값 M
    var inputUnit by remember { mutableStateOf("M") }
    //출력단위 초기값 M
    var outputUnit by remember { mutableStateOf("M") }
    //입력확장
    var inputExpanded by remember { mutableStateOf(false) }
    //출력확장
    var outputExpanded by remember { mutableStateOf(false) }
    // 입력값용 변환 변수 - 초기설정은 meter를 사용하기 위해 1.00으로
    val inputConversionFactor = remember { mutableDoubleStateOf(1.00) }
    // 출력값용 변환변수
    val outputConversionFactor = remember { mutableDoubleStateOf(1.00) }
    // 커스텀 스타일
    val customTextStyle = TextStyle(
        //sp는 주로 텍스트 크기를 정할때 사용 주로 4단위로 사용
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        letterSpacing = 1.5.sp
    )

    //단위 변환 시행 함수
    fun convertUnits(){
        //사용자입력값이 String형태의 Dobule숫자이면 Double로 변환하고 변환실패시 null반환
        //null반환할시에는 초기값 0.0을 반환
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        // (Double type 사용자 입력값 * Double type 입력변환변수 * 100.0 / 출력값용 변환변수)
        val result = (inputValueDouble * inputConversionFactor.value / outputConversionFactor.value)

        // BigDecimal을 사용하여 결과 값을 소수점 3자리까지 표현하고, 불필요한 소수점 0 제거
        val resultBigDecimal = BigDecimal(result).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros()

        // 소수점이 없는 정수이면 정수로, 아니면 소수점 3자리까지 표시 (끝자리 0은 제거됨)
        outputValue = resultBigDecimal.toPlainString()
    }
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
        Text(text = "Yoon's Unit Converter", style = customTextStyle)
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
                //텍스트상자의 값이 바뀔때마다 단위변환 함수 실행
                convertUnits()
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
                    //버튼이름에 클릭한 텍스트가 들어가도록 변수로지정
                    Text(text = "Input: $inputUnit")
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
                        //Dropdown 내부 항목 클릭했을때
                        onClick = {
                            inputExpanded = false
                            inputUnit = "M"
                            //변환 요소 숫자값 지정
                            inputConversionFactor.value = 1.00
                            //클릭했을때 단위변환 시행함수 호출
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("cm") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "cm"
                            //변환 요소 숫자값 지정
                            inputConversionFactor.value = 0.01
                            //클릭했을때 단위변환 시행함수 호출
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("mm") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "mm"
                            //변환 요소 숫자값 지정
                            inputConversionFactor.value = 0.001
                            //클릭했을때 단위변환 시행함수 호출
                            convertUnits()
                        }
                    )
                }
            }
            //두 버튼 사이 간격 주기
            //dp는 주로 레이아웃의 크기나 위치를 정의할때 사용 8, 16, 32 dp를 사용
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                //버튼을 눌렀을때 outputExpanded의 값을 true로 변경
                Button(onClick = { outputExpanded = true }) {
                    Text(text = "Output: $outputUnit")
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
                            //변환 요소 숫자값 지정
                            outputConversionFactor.value = 1.00
                            //클릭했을때 단위변환 시행함수 호출
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("cm") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "cm"
                            //변환 요소 숫자값 지정
                            outputConversionFactor.value = 0.01
                            //클릭했을때 단위변환 시행함수 호출
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("mm") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "mm"
                            //변환 요소 숫자값 지정
                            outputConversionFactor.value = 0.001
                            //클릭했을때 단위변환 시행함수 호출
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        //화면에 출력되는 결과 텍스트
        Text(text = "Convert Result: $outputValue $outputUnit"
        , style = MaterialTheme.typography.headlineMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConvertPreview() {
    //Composable + Preview(suffix)
    UnitConvert()
}