package choices;

import java.util.Arrays;

public class TestGAGEChoices {
    public static final Question NOYES = new Question(Arrays.asList("Нет", "Да"), Arrays.asList(0, 1));
    public static final Question SUBSTANCES = new Question(Arrays.asList("Нет", "Да (за исключением лекарств, выписанные врачом)"), Arrays.asList(0, 1));
    public static final Question LOSS_DOCUMENTS_WHEN = new Question(Arrays.asList("Больше года назад", "За последний год"), Arrays.asList(0, 1));
    public static final Question LOSS_DOCUMENTS_TIME = new Question(Arrays.asList("Один раз", "Больше одного"), Arrays.asList(0, 2));
    public static final Question DO_NOT_WORK_WHEN = new Question(Arrays.asList("Больше года назад", "Меньше года назад"), Arrays.asList(0, 1));
    public static final Question INJURIES = new Question(Arrays.asList("Нет", "Травмы серьезнее синяков", "Попадал в больницу из-за травм, полученных в состоянии опьянения"), Arrays.asList(0, 1, 3));
    public static final Question LOTS_ALCOHOL_TIME = new Question(Arrays.asList("Менее одного раза в полгода", "Более одного раза в полгода, реже раза в неделю", "Каждую неделю", "Каждый день"), Arrays.asList(0, 1, 2, 3));
    public static final Question DRINK_ALCOHOL_TIME = new Question(Arrays.asList("Реже одного раза в неделю", "Чаще одного раза в неделю", "Каждый день или «почти каждый день»"), Arrays.asList(0, 1, 2));
    public static final Question TRY_SUBSTANCES = new Question(Arrays.asList("Одно наименование", "Два и более наименований"), Arrays.asList(1, 2));
    public static final Question HOW_USE = new Question(Arrays.asList("Курил, нюхал, глотал", "Внутримышечные инъекции", "Внутривенные инъекции"), Arrays.asList(1, 2, 3));
    public static final Question POOR_HEALTH = new Question(Arrays.asList("Нет", "Да", "Да, требовалась медицинская помощь"), Arrays.asList(0, 1, 3));
    public static final Question COMPANY = new Question(Arrays.asList("В компании", "В одиночку"), Arrays.asList(1, 2));
}
