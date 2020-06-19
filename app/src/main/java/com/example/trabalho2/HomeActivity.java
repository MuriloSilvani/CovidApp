package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ImageView buttonBack;
    private Button buttonQuiz, buttonStats;

    private LinearLayout click1, click2, click3,  click4, click5, click6, click7, click8, click9;
    public TextView info1, info2, info3, info4, info5, info6, info7, info8, info9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        Bundle params = getIntent().getExtras();
        final Long user_id = params.getLong("user_id");

        buttonQuiz = (Button) findViewById(R.id.buttonQuiz);

        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent quizIntent = new Intent(HomeActivity.this, QuizActivity.class);
                quizIntent.putExtra("user_id", user_id);
                startActivity(quizIntent);
                finish();
            }
        });

        buttonStats = (Button) findViewById(R.id.buttonStats);

        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent statsIntent = new Intent(HomeActivity.this, FetchActivity.class);
                statsIntent.putExtra("user_id", user_id);
                startActivity(statsIntent);
                finish();
            }
        });

        buttonBack = (ImageView) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        info1 = (TextView) findViewById(R.id.info1);
        info2 = (TextView) findViewById(R.id.info2);
        info3 = (TextView) findViewById(R.id.info3);
        info4 = (TextView) findViewById(R.id.info4);
        info5 = (TextView) findViewById(R.id.info5);
        info6 = (TextView) findViewById(R.id.info6);
        info7 = (TextView) findViewById(R.id.info7);
        info8 = (TextView) findViewById(R.id.info8);
        info9 = (TextView) findViewById(R.id.info9);

        click1 = (LinearLayout) findViewById(R.id.click1);
        click2 = (LinearLayout) findViewById(R.id.click2);
        click3 = (LinearLayout) findViewById(R.id.click3);
        click4 = (LinearLayout) findViewById(R.id.click4);
        click5 = (LinearLayout) findViewById(R.id.click5);
        click6 = (LinearLayout) findViewById(R.id.click6);
        click7 = (LinearLayout) findViewById(R.id.click7);
        click8 = (LinearLayout) findViewById(R.id.click8);
        click9 = (LinearLayout) findViewById(R.id.click9);
        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info1.setText(user_id+"A COVID-19 é uma doença causada pelo coronavírus SARS-CoV-2, que apresenta um quadro clínico que varia de infecções assintomáticas a quadros respiratórios graves. De acordo com a Organização Mundial de Saúde (OMS), a maioria dos pacientes com COVID-19 (cerca de 80%) podem ser assintomáticos e cerca de 20% dos casos podem requerer atendimento hospitalar por apresentarem dificuldade respiratória e desses casos aproximadamente 5% podem necessitar de suporte para o tratamento de insuficiência respiratória (suporte ventilatório).");
            }
        });
        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info2.setText("Coronavírus é uma família de vírus que causam infecções respiratórias. O novo agente do coronavírus foi descoberto em 31/12/19 após casos registrados na China. Provoca a doença chamada de coronavírus (COVID-19).\n" +
                        "\n" +
                        "Os primeiros coronavírus humanos foram isolados pela primeira vez em 1937. No entanto, foi em 1965 que o vírus foi descrito como coronavírus, em decorrência do perfil na microscopia, parecendo uma coroa.\n" +
                        "\n" +
                        "A maioria das pessoas se infecta com os coronavírus comuns ao longo da vida, sendo as crianças pequenas mais propensas a se infectarem com o tipo mais comum do vírus. Os coronavírus mais comuns que infectam humanos são o alpha coronavírus 229E e NL63 e beta coronavírus OC43, HKU1.");
            }
        });
        click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info3.setText("Os sintomas da COVID-19 podem variar de um simples resfriado até uma pneumonia severa. Sendo os sintomas mais comuns:\n" +
                        "\n" +
                        "Tosse\n" +
                        "Febre\n" +
                        "Coriza\n" +
                        "Dor de garganta\n" +
                        "Dificuldade para respirar");
            }
        });
        click4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info4.setText("A transmissão acontece de uma pessoa doente para outra ou por contato próximo por meio de:\n" +
                        "• Toque do aperto de mão;\n" +
                        "• Gotículas de saliva;\n" +
                        "• Espirro;\n" +
                        "• Tosse;\n" +
                        "• Catarro;\n" +
                        "• Objetos ou superfícies contaminadas, como celulares, mesas, maçanetas, brinquedos, teclados de computador etc.");
            }
        });
        click5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info5.setText("O diagnóstico da COVID-19 é realizado primeiramente pelo profissional de saúde que deve avaliar a presença de critérios clínicos:\n" +
                        "\n" +
                        "Pessoa com quadro respiratório agudo, caracterizado por sensação febril ou febre, que pode ou não estar presente na hora da consulta (podendo ser relatada ao profissional de saúde), acompanhada de tosse OU dor de garganta OU coriza OU dificuldade respiratória, o que é chamado de Síndrome Gripal.\n" +
                        "Pessoa com desconforto respiratório/dificuldade para respirar OU pressão persistente no tórax OU saturação de oxigênio menor do que 95% em ar ambiente OU coloração azulada dos lábios ou rosto, o que é chamado de Síndrome Respiratória Aguda Grave\n" +
                        "Caso o paciente apresente os sintomas, o profissional de saúde poderá solicitar exame laboratoriais:\n" +
                        "\n" +
                        "De biologia molecular (RT-PCR em tempo real) que diagnostica tanto a COVID-19, a Influenza ou a presença de Vírus Sincicial Respiratório (VSR).\n" +
                        "Imunológico (teste rápido) que detecta, ou não, a presença de anticorpos em amostras coletadas somente após o sétimo dia de início dos sintomas.\n" +
                        "O diagnóstico da COVID-19 também pode ser realizado a partir de critérios como: histórico de contato próximo ou domiciliar, nos últimos 7 dias antes do aparecimento dos sintomas, com caso confirmado laboratorialmente para COVID-19 e para o qual não foi possível realizar a investigação laboratorial específica, também observados pelo profissional durante a consulta.\n" +
                        "\n");
            }
        });
        click6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info6.setText("As recomendações de prevenção à COVID-19 são as seguintes:\n" +
                        "\n" +
                        "Lave com frequência as mãos até a altura dos punhos, com água e sabão, ou então higienize com álcool em gel 70%.\n" +
                        "Ao tossir ou espirrar, cubra nariz e boca com lenço ou com o braço, e não com as mãos.\n" +
                        "Evite tocar olhos, nariz e boca com as mãos não lavadas.\n" +
                        "Ao tocar, lave sempre as mãos como já indicado.\n" +
                        "Mantenha uma distância mínima de cerca de 2 metros de qualquer pessoa tossindo ou espirrando.\n" +
                        "Evite abraços, beijos e apertos de mãos. Adote um comportamento amigável sem contato físico, mas sempre com um sorriso no rosto.\n" +
                        "Higienize com frequência o celular e os brinquedos das crianças.\n" +
                        "Não compartilhe objetos de uso pessoal, como talheres, toalhas, pratos e copos.\n" +
                        "Mantenha os ambientes limpos e bem ventilados.\n" +
                        "Evite circulação desnecessária nas ruas, estádios, teatros, shoppings, shows, cinemas e igrejas. Se puder, fique em casa.\n" +
                        "Se estiver doente, evite contato físico com outras pessoas, principalmente idosos e doentes crônicos, e fique em casa até melhorar.\n" +
                        "Durma bem e tenha uma alimentação saudável.\n" +
                        "Utilize máscaras caseiras ou artesanais feitas de tecido em situações de saída de sua residência.");
            }
        });
        click7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info7.setText("Caso você precise viajar, avalie a real necessidade. Se for inevitável viajar, previna-se e siga as orientações das autoridades de saúde locais.\n" +
                        "Ao voltar de viagens internacionais ou locais recomenda-se:\n" +
                        "\n" +
                        "No caso de viagens internacionais: o isolamento domiciliar voluntário por 7 dias após o desembarque, mesmo que não tenha apresentado os sintomas.\n" +
                        "No caso de viagens locais: ficar atento à sua condição de saúde, principalmente nos primeiros 14 dias.\n" +
                        "Reforçar os hábitos de higiene, como lavar as mãos com água e sabão.\n" +
                        "Caso apresente sintomas de gripe, siga as orientações do Ministério da Saúde para isolamento domiciliar.");
            }
        });
        click8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info8.setText("Caso você se sinta doente, com sintomas de gripe, evite contato físico com outras pessoas, principalmente idosos e doentes crônicos e fique em casa por 14 dias.\n" +
                        "Só procure um hospital de referência se estiver com falta de ar.\n" +
                        "\n" +
                        "Em caso de diagnóstico positivo para COVID-19, siga as seguintes recomendações:\n" +
                        "\n" +
                        "Fique em isolamento domiciliar.\n" +
                        "Utilize máscara o tempo todo.\n" +
                        "Se for preciso cozinhar, use máscara de proteção, cobrindo boca e nariz todo o tempo.\n" +
                        "Depois de usar o banheiro, nunca deixe de lavar as mãos com água e sabão e sempre limpe vaso, pia e demais superfícies com álcool ou água sanitária para desinfecção do ambiente.\n" +
                        "Separe toalhas de banho, garfos, facas, colheres, copos e outros objetos apenas para seu uso.\n" +
                        "O lixo produzido precisa ser separado e descartado.\n" +
                        "Sofás e cadeiras também não podem ser compartilhados e precisam ser limpos frequentemente com água sanitária ou álcool 70%.\n" +
                        "Mantenha a janela aberta para circulação de ar do ambiente usado para isolamento e a porta fechada, limpe a maçaneta frequentemente com álcool 70% ou água sanitária.\n" +
                        "Caso o paciente não more sozinho, os demais moradores da devem dormir em outro cômodo, longe da pessoa infectada, seguindo também as seguintes recomendações:\n" +
                        "\n" +
                        "Manter a distância mínima de 1 metro entre o paciente e os demais moradores.\n" +
                        "Limpe os móveis da casa frequentemente com água sanitária ou álcool 70%.\n" +
                        "Se uma pessoa da casa tiver diagnóstico positivo, todos os moradores ficam em isolamento por 14 dias também.\n" +
                        "Caso outro familiar da casa também inicie os sintomas leves, ele deve reiniciar o isolamento de 14 dias. Se os sintomas forem graves, como dificuldade para respirar, ele deve procurar orientação médica.");
            }
        });
        click9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaInfos();
                info9.setText("Procure um serviço de saúde apenas se apresentar falta de ar.\n" +
                        "\n" +
                        "Lista de hospitais que prestam atendimento em seu estado/município.\n" +
                        "\n" +
                        "Lista dos postos de saúde que prestam atendimento em seu estado/município.\n" +
                        "\n" +
                        "Laboratórios públicos de referência de testagem para coronavírus");
            }
        });




    }
    private void limpaInfos(){
        info1.setText("");
        info2.setText("");
        info3.setText("");
        info4.setText("");
        info5.setText("");
        info6.setText("");
        info7.setText("");
        info8.setText("");
        info9.setText("");
    }

}
