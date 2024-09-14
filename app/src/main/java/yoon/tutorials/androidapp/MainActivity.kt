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
        //textField
        //placeHolder, value입력시 실행함수를 인자로 가짐
        OutlinedTextField(value = "", onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //drowdown menu를 만들기 위해 box사용
            Box{
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "DropDown1")
                    //button의 type, 접근성
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                //dropdown menu setting
                //dropdown 확장 기본값 false 지정 , dropdown menu가 닫혔을때 실행될 함수
                //첫번째 버튼 드롭다운
                DropdownMenu(expanded =false , onDismissRequest = { /*TODO*/ }) {
                    //dropdown menu List
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("M") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("cm") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("mm") },
                        onClick = { /*TODO*/ }
                    )
                }
            }
            //두 버튼 사이 간격 주기
            //android interface는 주로 8, 16, 32 dp를 사용
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "DropDown2")
                    //button의 type, 접근성
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                //두번째 버튼 드롭다운
                DropdownMenu(expanded =false , onDismissRequest = { /*TODO*/ }) {
                    //dropdown menu List
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("M") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("cm") },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        //Composable 요구
                        text = { Text("mm") },
                        onClick = { /*TODO*/ }
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