package ru.vkn.appowar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FightCommandTest {

    @Test
    void moveForward() {
        /*
         1. Написать тест на команду передвижение (ожидаем что изменяется
        позиция) саму команду пока мокнуть - и написать ассерты на
         ожидаемое поведение(и замокать сам объект позиция потом его
          реализовать тоже(и все мокать пока, потом моки реализовывать
          уже возможно с другими тестами) - короче все замокать а команду
           MoveCommand сделать пустым классом что бы скомпилировалось.
            2. Написать реализацию этой команды. Позицию реализовать вместе с
            реализацией класса команды (посмотреть как лучше из нее ноги вырастут
             в другие места)
         */
        //pre
        var fightCommand = mock(FightCommand.class);
        var posititon = mock(Posititon.class);
        var technicalUnitSource = mock(TacticalUnit.class);
        var technicalUnitTarget = mock(TacticalUnit.class);

        //when
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);

        //then
        assertThat(posititon.get(technicalUnitSource))
                .isEqualTo(3L);
    }
}