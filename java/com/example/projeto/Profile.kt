package com.example.projeto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.widget.*
class ProfileActivity : AppCompatActivity() {

    private lateinit var spinnerPeso: Spinner
    private lateinit var spinnerIdade: Spinner
    private lateinit var spinnerSemana: Spinner
    private lateinit var etExtras: EditText
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inicialização dos componentes
        spinnerPeso = findViewById(R.id.pesos_kg)
        spinnerIdade = findViewById(R.id.spinneridade)
        spinnerSemana = findViewById(R.id.spinnersemana)
        etExtras = findViewById(R.id.etName)
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)

        // Preenche os spinners com os arrays do strings.xml
        setupSpinners()

        // Carrega dados salvos
        loadProfile()

        // Salva ao sair da tela
        // (poderia ser também com um botão, se preferir)
    }

    override fun onPause() {
        super.onPause()
        saveProfile()
    }

    private fun setupSpinners() {
        val adapterPeso = ArrayAdapter.createFromResource(
            this, R.array.pesos_kg, android.R.layout.simple_spinner_item
        )
        adapterPeso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPeso.adapter = adapterPeso

        val adapterIdade = ArrayAdapter.createFromResource(
            this, R.array.idade, android.R.layout.simple_spinner_item
        )
        adapterIdade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerIdade.adapter = adapterIdade

        val adapterSemana = ArrayAdapter.createFromResource(
            this, R.array.semana, android.R.layout.simple_spinner_item
        )
        adapterSemana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSemana.adapter = adapterSemana
    }

    private fun saveProfile() {
        val sharedPref = getSharedPreferences("perfil", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("peso_pos", spinnerPeso.selectedItemPosition)
            putInt("idade_pos", spinnerIdade.selectedItemPosition)
            putInt("semana_pos", spinnerSemana.selectedItemPosition)
            putString("extras", etExtras.text.toString())
            apply()
        }
    }

    private fun loadProfile() {
        val sharedPref = getSharedPreferences("perfil", Context.MODE_PRIVATE)
        spinnerPeso.setSelection(sharedPref.getInt("peso_pos", 0))
        spinnerIdade.setSelection(sharedPref.getInt("idade_pos", 0))
        spinnerSemana.setSelection(sharedPref.getInt("semana_pos", 0))
        etExtras.setText(sharedPref.getString("extras", ""))
    }
}
