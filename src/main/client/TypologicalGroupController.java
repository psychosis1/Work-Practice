package main.client;

import database.TestBoykoTable;
import database.TestGAGETable;
import database.TypologicalGroupTable;
import entity.TestBoyko;
import entity.TestGAGE;
import entity.TypologicalGroup;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import main.Alerts;
import properties.Current;

import java.util.Optional;

public class TypologicalGroupController {
    @FXML
    private Label title;
    @FXML
    private TextArea text;

    private final TypologicalGroup group = new TypologicalGroup();

    private final TestBoyko testBoyko = new TestBoyko();
    private final TestGAGE testGAGE = new TestGAGE();

    private TypologicalGroupTable table = new TypologicalGroupTable();

    private final TestGAGETable gageTable = new TestGAGETable();

    private final TestBoykoTable boykoTable = new TestBoykoTable();

    /**
     * Инициализация окна
     */
    @FXML
    public void initialize() {
        askAttempt(); //установка диагностики

        select();
    }

    /**
     * Выбор диагностики
     */
    private void askAttempt() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Первичная", "Вторичная");
        dialog.setTitle("Диагностика");
        dialog.setHeaderText(null);
        dialog.setContentText("Диагностика:");

        Alerts.setIcon(dialog);

        Optional<String> result = dialog.showAndWait();


        result.ifPresentOrElse(
                (attempt)
                        -> {
                    if (attempt.equals("Первичная")) {
                        testBoyko.setAttempt(1);
                        testGAGE.setAttempt(1);
                        title.setText("Типологическая группа (Первичная диагностика)");
                    } else {
                        testBoyko.setAttempt(2);
                        testGAGE.setAttempt(2);
                        title.setText("Типологическая группа (Вторичная диагностика)");
                    }
                },
                ()
                        -> {
                    testBoyko.setAttempt(1);
                    testGAGE.setAttempt(1);
                    title.setText("Типологическая группа (Первичная диагностика)");
                });

        //установка клиента
        testGAGE.setClient(Current.CLIENT.getIdClient());
        testBoyko.setClient(Current.CLIENT.getIdClient());
        group.setClient(Current.CLIENT.getIdClient());
    }

    /**
     * Выбор тестов
     *
     * @return Булевое значение
     */
    private boolean selectTests() {
        if (boykoTable.selectTestBoyko(testBoyko) > 0 && gageTable.selectTestGAGE(testGAGE) > 0) {
            group.setTest_boyko(testBoyko.getIdTestBoyko());
            group.setTest_gage(testGAGE.getIdTestGAGE());
            return true;
        }
        return false;
    }

    /**
     * Установка группы
     *
     * @return Булевое значение
     */
    private boolean setGroup() {
        if (selectTests()) {

            if (
                    testBoyko.getMemory_disorder() == 0 &&
                            (testBoyko.getWork_activity() == 0 ||
                                    testBoyko.getWork_activity() == 1) &&
                            Current.CLIENT.getHiv_status() == 0 &&
                            Current.CLIENT.getUse_last_month() == 0
            ) {
                group.setGroup(1);
                if (
                        Current.CLIENT.getAlcohol_last_month() == 0 &&
                                testGAGE.getSubstances() == 0 &&
                                Current.CLIENT.getAge() >= 50
                )
                    group.setSubgroup(1);
                else if (
                        Current.CLIENT.getAlcohol_last_month() == 1 &&
                                Current.CLIENT.getHepatitis_c() == 0
                )
                    group.setSubgroup(2);
                else if (
                        testGAGE.getSubstances() == 1 &&
                                Current.CLIENT.getHepatitis_c() == 1
                )
                    group.setSubgroup(3);

                else group.setSubgroup(0);
            } else if (
                    testBoyko.getAggressiveness() >= 1 &&
                            testBoyko.getWork_activity() == 4 &&
                            Current.CLIENT.getEducation() <= 4
            ) {
                group.setGroup(2);
                group.setSubgroup(0);
            } else if (
                    Current.CLIENT.getHiv_status() == 1 &&
                            testGAGE.getSubstances() == 1
            ) {
                group.setGroup(3);
                group.setSubgroup(0);
            } else {
                group.setGroup(0);
                group.setSubgroup(0);
            }

            return true;

        } else {
            Alerts.warning("Невозможно создать типологическую группу", "Тест Бойко или тест GAGE текущей попытки отсутствуют.");
            return false;
        }
    }

    /**
     * Выбор группы
     */
    private void select() {
        if (table.select(group, testBoyko.getAttempt()))
            update();
        else insert();
    }

    /**
     * Вставка группы
     */
    private void insert() {
        if (setGroup()) {
            table.insert(group);
            getText();
        }
    }

    /**
     * Обновление группы
     */
    private void update() {
        if (setGroup()) {
            table.update(group);
            getText();
        }
    }

    /**
     * Получение текста
     */
    private void getText() {
        switch (group.getGroup()) {
            case 0 -> {
                text.setText("Невозможно определить типологическую группу");
                text.setStyle("-fx-font-weight: bold");
            }
            case 1 -> {
                text.setText("ПСУ относится к типологической группе «Группа употребляющих преимущественно алкоголь с относительно  высоким уровнем социально-реабилитационного потенциала». \n" +
                        "\n" +
                        "Для этой группы характерно употребление алкоголя и не употребление наркотиков. Эти мужчины имеют полное общее и среднее-специальное образование, не имеют выраженных когнитивных нарушений, работают на постоянной или временной работе (без больших перерывов в работе), нет выраженных трудностей во взаимодействии с окружающими. Социально значимые заболевания (ВИЧ, гепатит С) отсутствуют или встречаются редко. Из факторов, затрудняющих реадаптацию можно выделить отсутствие связей с семьей. Не все ПСУ этой группы одиноки, но стабильные семейные связи либо утрачены, либо никогда не были установлены. Можно предполагать, что чувство одиночества, изолированности, в сочетании со стрессовой ситуацией могут предрасполагать к употреблению алкоголя.\n" +
                        "\n" +
                        "Рекомендации по взаимодействию\n" +
                        "- Содействие в профориентации, краткосрочной профессиональной подготовке и трудоустройстве, в том числе перенаправление к специалистам государственных органов по труду, занятости и социальной защите; \n" +
                        "- Мотивирование на активные действия по решению проблем с опорой на имеющиеся сильные стороны, обучение необходимым социальным навыкам, по необходимости социальное посредничество в разрешении конфликтных ситуаций в семье или на работе, содействие в получении материальной помощи.\n" +
                        "- Социально-правовое сопровождение ПСУ, в том числе консультирование по вопросам законодательства, направление в юридические службы для составления по необходимости ходатайств, жалоб, справок и т.п., содействие в восстановлении утраченных документов по необходимости.\n" +
                        "- Различные формы групповой работы (групповое консультирование;  группы поддержки; группы взаимопомощи). Основная цель группового консультирования состоит в  информировании освободившихся из МЛС по актуальным для них вопросам. Сюда могут относиться социально-правовые, социально-медицинские, социально-бытовые и психологические вопросы. Такие группы могут проводиться совместно психологом и специалистом по социальной работе либо только одним из специалистов. Группы поддержки дают возможность ПСУ рассказать друг другу о насущных проблемах и поделиться личным опытом их решения. Основная задача специалиста, организующего группы поддержки создавать и поддерживать безопасное пространство, где бывшие осужденные могут открыто обсуждать тревожащие их вопросы и делиться своими чувствами. Эта форма работы дает возможность почувствовать единство с людьми, оказавшимися в похожей жизненной ситуации, и таким образом получить психологическую и эмоциональную поддержку. Ведущим таких групп на первых этапах должен быть психолог, который, направляя работу группы, должен быть способен учитывать реальные потребности участников и не навязывать своего мнения. Работа группы взаимопомощи, в отличие от группы поддержки, состоит в том, что специалист не является ведущим, а становится лишь фасилитатором-помощником, который следит за соблюдением правил и поддерживает процесс плодотворного обсуждения в группе. Если, согласно оценке психолога, участники группы способны самостоятельно регулировать процесс группового взаимодействия, роль фасилитатора берет на себя кто-то из участников. В группе взаимопомощи ответственность и контроль от ведущего передаются участникам группы, что в значительной мере способствует активизации их собственных ресурсов. Часто, сформированная группа из числа бывших осужденных, проходит постепенный путь от этапа информирования (групповое консультирование) через работу в группах поддержки к объединению в последующем в группы взаимопомощи. Таким образом, растет самостоятельность ПСУ, их способность поддерживать друг друга, формируются коммуникативные и лидерские навыки.\n");
                switch (group.getSubgroup()) {
                    case 1 -> text.setText("ПСУ относится к типологической группе «Пожилые потребители алкоголя со снижением социального функционирования». \n" +
                            "\n" +
                            "В эту группу входят мужчины старше 50 лет. Даже не имея признаков злоупотребления алкоголем, не употребляя наркотики, с возрастом освободившиеся из мест лишения свободы имеют более выраженные, по сравнению с более молодыми, трудности социальной адаптации. Их отношения с окружающими характеризуются большей отгороженностью, замкнутостью, даже грубостью, им труднее устанавливать новые контакты, и они, как правило, не сохраняют контактов с семьей и родственниками. Родители этих мужчин уже умерли, другие родственники и семейные связи или вовсе отсутствовали или утрачены. Иногда, видимо в силу возраста, у них могут возникать отдельные трудности в самообслуживании, которые по мере старения могут возрастать. Все перечисленное делает социальное функционирование этой группы в целом более затрудненным.\n" +
                            "\n" +
                            "Рекомендации по взаимодействию\n" +
                            "- Социально-консультативная помощь, направленная на лучшее узнавание изменившегося за время пребывания в МЛС в бытовом и профессиональном плане жизни города.\n" +
                            "- Развитие опоры на собственные силы за счет актуализации имеющихся рабочих, бытовых и психологических навыков, поддержка ценности приобретенного опыта.\n" +
                            "- Помощь в интеграции жизненного пути и выстраивание положительной жизненной перспективы (например, за счет обсуждения успешных случаев реадаптации, построения конкретных четких и небольших целей и задач на будущее).\n" +
                            "- Помощь в понимании ПСУ собственного способа решения жизненных задач (побуждать самоанализ переживаний, ценностей и ресурсов).\n" +
                            "- Информирование  об имеющихся возможностях помощи, и обучение навыкам обращаться за помощью (в том числе, отстаиванию своих прав, наилучшему представлению своих навыков при поиске работы, алгоритмам обращения в различные инстанции).\n" +
                            "- Сопровождение постановки кратко- и среднесрочных целей с учётом страха глобальных изменений (в трудовой сфере, в области заботы о своем здоровье, установлении отношений с близкими, решении различных бытовых проблем). \n" +
                            "- Сопровождение горевания об утрате полноценной взрослой жизни, невозможности возврата и аннулирования прошлого.\n" +
                            "- Содействие в получении медицинской помощи для разрешения накопившихся часто хронических проблем со здоровьем до момента обострения.\n" +
                            "- Обсуждение четких условия проживания в Центре и акцентирование выбора ПСУ следовать им, обсуждение близких или отдаленных последствий принятых решений.\n" +
                            "- Помощь  и побуждение к самоанализу, в частности,  искать и обсуждать сложные потенциально дезадаптирующие ситуации, которые могут спровоцировать употребление алкоголя. Выявлять триггерные точки начала употребления. Употребление алкоголя может рассматриваться ПСУ как способ «снятия стресса» и напряжения. \n" +
                            "- Обучение навыкам саморегуляции.\n" +
                            "- Медико-социальное просвещение.\n" +
                            "- Помощь в поиске и присоединению к просоциальным сообществам (группы, клуба по интересам).\n");
                    case 2 -> text.setText("ПСУ относится к типологической группе «Группа риска формирования групповой зависимости от алкоголя при относительно высоком социально-реабилитационном потенциале в целом».  \n" +
                            "\n" +
                            "Эти  мужчины имеют больший стаж употребления и более выраженный риск злоупотребления алкоголем. При этом у них есть опыт отношений и брака (в настоящий момент находятся в разводе или в новых отношениях). Их основная уязвимость в плане социальной реадаптации может быть связан формированием и развитием групповой зависимости от ПАВ. Эта форма зависимости предполагает, что употребление компульсивно начинается  как только человек попадает в употребляющую компанию.\n" +
                            "\n" +
                            "Рекомендации по взаимодействию\n" +
                            "- Обсуждение четких условий проживания в Центре, в частности полного отказа от употребления ПАВ во время проживания в стационаре, и акцентирование выбора ПСУ следовать им, обсуждение близких и отдаленных последствий принятых решений.\n" +
                            "- Формирование антинаркотической среды в Центре (включая контроль за исполнением запрета на употребление и нахождение в состоянии опьянения на территории).\n" +
                            "- Помощь  и побуждение к самоанализу, в частности, искать и обсуждать сложные потенциально дезадаптирующие ситуации, которые могут спровоцировать употребление алкоголя. Выявлять триггерные точки начала употребления. Содействовать осознаванию риска нахождения в компании потребителей ПАВ. \n" +
                            "- Основное внимание стоит сосредоточить на формирование и поддержание просоциальных ценностей и вовлечение в просоциальные сообщества (включая АА, групповые формы досуга и т.д.).\n");
                    case 3 -> text.setText("Получатель относится к типологической группе «Группа мужчин с опытом употребления наркотиков и относительно высоким реабилитационным потенциалом».\n" +
                            "\n" +
                            "В нее входят мужчины с относительно высокими значениями злоупотребления ПАВ, в первую очередь алкоголем, некоторые имеют и опыт употребления наркотиков.  Социально-адаптационный потенциал этой группы опирается на способность работать достаточно стабильно, чтобы иметь возможность снимать жилье, но ослабляется медицинскими проблемами, в том числе гепатитом С. Эти опрошенные нуждаются в том числе в содействии получению медицинской помощи.\n" +
                            "\n" +
                            "Рекомендации по взаимодействию\n" +
                            "- Консультирование по медицинским, правовым, социальным и иным вопросам, связанным с диагнозом гепатит С.\n" +
                            "- Поддержка приверженности лечению гепатита и диспансерному наблюдению. \n" +
                            "- Поддержка обращения за помощью в организации и самоорганизованные группы (например, группы анонимных алкоголиков) за поддержкой на пути сохранения здоровья и трезвости.\n");
                }
            }
            case 2 -> text.setText("Получатель относится к типологической группе «Группа риска групповой зависимости с относительно низким социально-реабилитационном потенциалом». \n" +
                    "\n" +
                    "Это мужчины, чье употребление алкоголя и других психоактивных веществ имеют больше вредных последствий и происходит преимущественно в компании. Их образовательный уровень ограничивается общим средним образованием (школа), имеются трудности с нахождением постоянной работы, чаще перебиваются временными и разовыми подработками. В общении с другими людьми для этой группы характерно более грубая, фамильярная манера.\n" +
                    "\n" +
                    "Рекомендации по взаимодействию:\n" +
                    "- Обучение бытовым навыкам и формирование интереса к образованию и самообразованию.\n" +
                    "- Вовлечение в группы поддержки трезвого образа жизни (в частности, АА, АН), и мотивационное консультирование (включая направление к консультантам по химическим зависимостям).\n" +
                    "- Консультирование по вопросам профориентации и профессионального обучения.\n" +
                    "- Оказание гуманитарной помощи  (с целью поддержки мотивации обращения к помогающим специалистам).\n" +
                    "- Просвещение и обучение навыкам общения и саморегуляции.\n" +
                    "- Формирование и поддержание просоциальных ценностей и вовлечение в просоциальные сообщества, защита от присутствия дилеров психоактивных веществ и их открытого употребления на территории Центра.\n");
            case 3 -> text.setText("Получатель относится к типологической группе «Группа зависимых от алкоголя и наркотиков с высоким стажем». \n" +
                    "\n" +
                    "Их употребление по сравнению с другими типологическими группами более длительно, больше выражены вредные последствия, в том числе в виде ВИЧ-инфекции и гепатита С.  Эти опрошенные имеют более длительный опыт употребления психоактивных веществ и более узкий круг общения.\n" +
                    "\n" +
                    "Рекомендации по взаимодействию\n" +
                    "- Консультирование по медицинским, правовым, социальным и иным вопросам, связанным с диагнозом ВИЧ-инфекция и гепатит С.\n" +
                    "- Поддержка приверженности лечению ВИЧ и гепатита С и диспансерному наблюдению,  в том числе с привлечением равных консультантов).\n" +
                    "- Мотивационное консультирование, направленное на формирование ответственного поведения ПСУ в отношении собственного здоровья и предупреждение дальнейшей передачи ВИЧ.\n" +
                    "- Мотивационное консультирование, направленное на отказ от употребления психоактивных веществ.\n" +
                    "- Организация групповых информационно-просветительских мероприятий по вопросам жизни с ВИЧ.\n");
        }
    }
}
