package com.example.sistemadenotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemadenotas.ui.theme.SistemaDeNotasTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SistemaDeNotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(
            onLoginSuccess = {
                currentScreen = "grades"
            }
        )

        "grades" -> GradesInputScreen(
            onBack = {
                currentScreen = "login"
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Inicio de Sesión",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (email == "admin.notas@gmail.com" && password == "12345") {
                    errorMessage = ""
                    onLoginSuccess()
                } else {
                    errorMessage = "Correo o contraseña incorrectos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        AlertMessage(message = errorMessage, isError = true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradesInputScreen(onBack: () -> Unit) {
    var studentName by remember { mutableStateOf("") }
    var note1 by remember { mutableStateOf("") }
    var note2 by remember { mutableStateOf("") }
    var note3 by remember { mutableStateOf("") }
    var savedMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ingreso de Notas",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Nombre del estudiante") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = note1,
            onValueChange = {
                if (it.matches(Regex("^\\d{0,2}(\\.\\d{0,2})?$"))) {
                    note1 = it
                }
            },
            label = { Text("Nota 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = note2,
            onValueChange = {
                if (it.matches(Regex("^\\d{0,2}(\\.\\d{0,2})?$"))) {
                    note2 = it
                }
            },
            label = { Text("Nota 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = note3,
            onValueChange = {
                if (it.matches(Regex("^\\d{0,2}(\\.\\d{0,2})?$"))) {
                    note3 = it
                }
            },
            label = { Text("Nota 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val grade1 = note1.toFloatOrNull()
                val grade2 = note2.toFloatOrNull()
                val grade3 = note3.toFloatOrNull()

                if (studentName.isBlank() || note1.isBlank() || note2.isBlank() || note3.isBlank()) {
                    savedMessage = "Complete todos los campos antes de guardar"
                } else if (grade1 == null || grade2 == null || grade3 == null) {
                    savedMessage = "Las notas deben ser valores numéricos"
                } else if (grade1 !in 0f..10f || grade2 !in 0f..10f || grade3 !in 0f..10f) {
                    savedMessage = "Las notas deben estar entre 0 y 10"
                } else {
                    savedMessage = "Notas guardadas correctamente para $studentName: $grade1, $grade2, $grade3"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar notas")
        }
        AlertMessage(
            message = savedMessage,
            isError = !savedMessage.contains("correctamente")
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}

@Composable
fun AlertMessage(
    message: String,
    isError: Boolean = true
) {
    if (message.isNotEmpty()) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = message,
            color = if (isError) Color(0xFFB00020) else Color(0xFF1B5E20),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isError) Color(0xFFFFEBEE) else Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        )
    }
}