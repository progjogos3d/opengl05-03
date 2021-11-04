# Aula 05 - Iluminação: Componentes especular e materiais

Neste repositório apresentamos o código intermediário da aula de iluminação contendo também o componente especular e os 
materiais.

## Componente especular

Representa a luz que reflete no objeto diretamente sobre os olhos do observador. Isso faz com que um brilho intenso 
apareça. Normalmente, a cor do reflexo especular será branco, a menos que o objeto seja um metal. A propriedade do 
material specular power controla o quão disperso é o brilho.

## Materiais

Para cada componente da luz, permitimos especificar como o material absorve os valores de R, G e B. Assim como foi feito
com a luz, o material também é aplicado através de uma operação de produto. Assim, por exemplo, se uma luz brilha com 
os valores 0.8, 0.8, 0.4 em R, G e B e o material reflete 50% de R, 80% de verde e nenhum azul, o material será 
especificado por 0.5, 0.8, 0.0. Portanto, a cor final será 0.8*0.5, 0.8*0.8, 0.4*0.0 = 0.4, 0.64, 0.0.
