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


    var registeredEmail by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }
    var loggedUserName by remember { mutableStateOf("") }
    
    var average by remember { mutableStateOf(0f) }

    when (currentScreen) {
        "login" -> LoginScreen(
            registeredEmail = registeredEmail,
            registeredPassword = registeredPassword,
            onLoginSuccess = { email ->
                loggedUserName = email.substringBefore("@")
                currentScreen = "welcome"
            },
            onGoToRegister = {
                currentScreen = "register"
            }
        )

        "register" -> RegisterScreen(
            onRegisterSuccess = { email, password ->
                registeredEmail = email
                registeredPassword = password
                currentScreen = "login"
            },
            onBackToLogin = {
                currentScreen = "login"
            }
        )

        "welcome" -> WelcomeScreen(
            userName = loggedUserName,
            onGoToGrades = {
                currentScreen = "grades"
            },
            onLogout = {
                currentScreen = "login"
            }
        )

        "grades" -> GradesInputScreen(
            studentName = loggedUserName,
            onBack = {
                currentScreen = "welcome"
            },
            onCalculate = { avg ->
                average = avg
                currentScreen = "result"
            }
        )

        "result" -> ResultScreen(
            average = average,
            onBack = {
                currentScreen = "grades"
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    registeredEmail: String,
    registeredPassword: String,
    onLoginSuccess: (String) -> Unit,
    onGoToRegister: () -> Unit
) {
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
                if (email == registeredEmail && password == registeredPassword) {
                    errorMessage = ""
                    onLoginSuccess(email)
                } else {
                    errorMessage = "Correo o contraseña incorrectos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        AlertMessage(message = errorMessage, isError = true)

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onGoToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: (String, String) -> Unit,
    onBackToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registro de Usuario",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                message = when {
                    email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
                        "Complete todos los campos"

                    !email.contains("@") ->
                        "Ingrese un correo válido"

                    password.length < 5 ->
                        "La contraseña debe tener al menos 5 caracteres"

                    password != confirmPassword ->
                        "Las contraseñas no coinciden"

                    else -> {
                        onRegisterSuccess(email, password)
                        ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackToLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al login")
        }

        AlertMessage(
            message = message,
            isError = true
        )
    }
}

 @Composable
 fun WelcomeScreen(
     userName: String,
     onGoToGrades: () -> Unit,
     onLogout: () -> Unit
 ) {
     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(24.dp),
         verticalArrangement = Arrangement.Center
     ) {
         Text(
             text = "Bienvenido, $userName",
             fontSize = 30.sp
         )

         Spacer(modifier = Modifier.height(12.dp))

         Text(
             text = "Desde este panel puede acceder al registro de notas del estudiante.",
             fontSize = 16.sp
         )

         Spacer(modifier = Modifier.height(24.dp))

         Button(
             onClick = onGoToGrades,
             modifier = Modifier.fillMaxWidth()
         ) {
             Text("Ir al registro de notas")
         }

         Spacer(modifier = Modifier.height(12.dp))

         Button(
             onClick = onLogout,
             modifier = Modifier.fillMaxWidth()
         ) {
             Text("Cerrar sesión")
         }
     }
 }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradesInputScreen(
    studentName: String,
    onBack: () -> Unit,
    onCalculate: (Float) -> Unit
) {
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

        Text(
            text = "Estudiante: $studentName",
            fontSize = 18.sp
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
            label = { Text("Nota 2") },
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
            label = { Text("Nota 3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val grade1 = note1.toFloatOrNull()
                val grade2 = note2.toFloatOrNull()
                val grade3 = note3.toFloatOrNull()

                if (note1.isBlank() || note2.isBlank() || note3.isBlank()) {
                    savedMessage = "Complete todos los campos antes de guardar"
                } else if (grade1 == null || grade2 == null || grade3 == null) {
                    savedMessage = "Las notas deben ser valores numéricos"
                } else if (grade1 !in 0f..10f || grade2 !in 0f..10f || grade3 !in 0f..10f) {
                    savedMessage = "Las notas deben estar entre 0 y 10"
                } else {
                    val average = (grade1 + grade2 + grade3) / 3
                    onCalculate(average)
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
            Text("Regresar")
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

@Composable
fun ResultScreen(
    average: Float,
    onBack: () -> Unit
) {
    val resultText = if (average >= 6f) "Aprobó" else "Reprobó"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Resultado", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Promedio: %.2f".format(average),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = resultText,
            fontSize = 22.sp,
            color = if (average >= 6f) Color(0xFF1B5E20) else Color(0xFFB00020)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}