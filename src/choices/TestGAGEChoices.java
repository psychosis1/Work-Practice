package choices;

import java.util.Arrays;

public class TestGAGEChoices {
    public static final TestGAGEQuestion NOYES = new TestGAGEQuestion(Arrays.asList("Нет", "Да"), Arrays.asList(0, 1));
    public static final TestGAGEQuestion SUBSTANCES = new TestGAGEQuestion(Arrays.asList("Нет", "Да (за исключением лекарств, выписанные врачом)"), Arrays.asList(0, 1));
    public static final TestGAGEQuestion LOSS_DOCUMENTS_WHEN = new TestGAGEQuestion(Arrays.asList("Больше года назад", "За последний год"), Arrays.asList(0, 1));
    public static final TestGAGEQuestion LOSS_DOCUMENTS_TIME = new TestGAGEQuestion(Arrays.asList("Один раз", "Больше одного"), Arrays.asList(0, 2));
    public static final TestGAGEQuestion DO_NOT_WORK_WHEN = new TestGAGEQuestion(Arrays.asList("Больше года назад", "Меньше года назад"), Arrays.asList(0, 1));
    public static final TestGAGEQuestion INJURIES = new TestGAGEQuestion(Arrays.asList("Нет", "Травмы серьезнее синяков", "Попадал в больницу из-за травм, полученных в состоянии опьянения"), Arrays.asList(0, 1, 3));
    public static final TestGAGEQuestion LOTS_ALCOHOL_TIME = new TestGAGEQuestion(Arrays.asList("Менее одного раза в полгода", "Более одного раза в полгода, реже раза в неделю", "Каждую неделю", "Каждый день"), Arrays.asList(0, 1, 2, 3));
    public static final TestGAGEQuestion DRINK_ALCOHOL_TIME = new TestGAGEQuestion(Arrays.asList("Реже одного раза в неделю", "Чаще одного раза в неделю", "Каждый день или «почти каждый день»"), Arrays.asList(0, 1, 2));
    public static final TestGAGEQuestion TRY_SUBSTANCES = new TestGAGEQuestion(Arrays.asList("Одно наименование", "Два и более наименований"), Arrays.asList(1, 2));
    public static final TestGAGEQuestion HOW_USE = new TestGAGEQuestion(Arrays.asList("Курил, нюхал, глотал", "Внутримышечные инъекции", "Внутривенные инъекции"), Arrays.asList(1, 2, 3));
    public static final TestGAGEQuestion POOR_HEALTH = new TestGAGEQuestion(Arrays.asList("Нет", "Да", "Да, требовалась медицинская помощь"), Arrays.asList(0, 1, 3));
    public static final TestGAGEQuestion COMPANY = new TestGAGEQuestion(Arrays.asList("В компании", "В одиночку"), Arrays.asList(1, 2));
}
