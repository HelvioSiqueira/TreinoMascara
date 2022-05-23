# Máscara no TextInput

O app exemplifica a aplicação de uma máscara no *TextInput* de um celular. Uso a função ```onTextChanged(CharSequence s, int start, int before, int count)```
presente no view do tipo *TextInput*. Quando apliquei a máscara foi preciso aplica-la indo(digitando)que usei como base o exemplo dado no livro Dominando o Android
com Kotlin do Nelson Glauber:

```
if(count > before){
                    if(str.length > 7){
                        str = "(${str.substring(0, 2)}) ${str.substring(2, 7)}-${str.substring(7)}"
                    } else if(str.length > 3){
                        str = "(${str.substring(0, 2)}) ${str.substring(2)}"
                    } else if(str.length > 2){
                        str = "(${str.substring(0, 2)}) ${str.substring(2)}"
                    } else if(str.length > 1){
                        str = "(${str.substring(0)})"
                    }

                    isUpdating = true
                    inputCelular.setText(str)
                    inputCelular.setSelection(inputCelular.text?.length ?: 0)

                }
```

e tive que me virar na parte voltando(excluindo):

```
else if (before > count){
                    if(str.length > 7){
                        str = "(${str.substring(0, 2)}) ${str.substring(2, 7)}-${str.substring(7)}"
                    } else if(str.length in 3..7){
                        str = "(${str.substring(0, 2)}) ${str.substring(2, str.length)}"
                    } else if(str.length in 1..2){
                        str = "(${str.substring(0, str.length)}"
                    } else if(str.length <= 1){
                        str = ""
                    }

                    isUpdating = true
                    inputCelular.setText(str)
                    inputCelular.setSelection(inputCelular.text?.length ?: 0)

                
```

## Explicando

No ```onTextChanged(CharSequence s, int start, int before, int count)``` uso os paramentros ```before``` e ```count``` para detectar se o usuario está digitando ou
excluindo; caso *count*(quantidade de digitos atual) seja maior que *before*(quantidade de digitos anterior) então o usuário está digitando, caso contrário
então o usuário está excluindo. Então depois só precisei aplicar a logica da formatação do digitos.

## Dificuldade

Aplicando a máscara no indo não tive nenhum problema 
pois eu sempre conseguia setar os indices do digitos que iam aparecer com o ```${str.substring(0, 2)}``` no contrário da máscara voltando, quando eu usava
esse formato o app crachava na hora, pois eu iria tá tentando mostrar um digito que não existia mais! E então eu tive a ideia de usar o ```str.length```
pra indicar até onde iria o índice da string a ser exibida, e nesse caso ela sempre seria igual ao tamanho da própria string.
