public class Epidemic {
    private static long[] sick = new long[50];
    private static long[] notSick = new long[50];

    public static void main(String[] args) {
//        int days = 30;
//        long maxSick = 0;
//        int dayWhenMaxSick = 0;
//        epidemicDivideAndConquerWith14Days(days);
//        for (int i = 1; i <= days; i++) {
//            if (sick[i] - notSick[i] == 0) {
//                System.out.println("In " + (i) + " day epidemic ends");
//                break;
//            }
//            if (sick[i] > maxSick && sick[i] < 100_000) {
//                maxSick = sick[i];
//                dayWhenMaxSick = i; // tutaj trzeba rozmkminic to zeby nie bylo ujemnych tylko jak przekroczy 100k to znaczy ze zachorwan jest mniej itd
//            }
//        }
//
//        System.out.println("SICK : ");
//        for (int i = 1; i <= days; i++) {
//            System.out.print(sick[i] + " "); // jesli wyprintuje wynik ujemny to oznacza, ze wiecej luzi wzydrowialo niz zachorowalo
//        }
//        System.out.println();
//
//        System.out.println("NOT SICK: ");
//        for (int i = 8; i <= days; i++) {
//            System.out.print(notSick[i] + " "); // jesli wyprintuje wynik ujemny to oznacza, ze wiecej luzi zachorowalo niz wyzdorwialo
//        }
//        System.out.println();
//        System.out.println("Most sick people in day: " + dayWhenMaxSick + " amount of people: " + maxSick);
//        epidemicDynamicProgramming(28); // w dniu 19 epidemia się kończy, ponieważ ilość zdrowych przewyższa ilość chorych

    }

    private static void epidemicDynamicProgramming(int day) {
        long[] sickPeople = new long[day+1];
        long[] notSickPeople = new long[day+1]; // ozdrowiency
        sickPeople[1] = 10; // first day
        if(day < 7) {
            for(int i = 1; i <= day; i++) {
                notSickPeople[i] = 0;
            }
        } else {
            for(int i = 1; i <= 7; i++) {
                notSickPeople[i] = 0;
            }
        }

        for(int i = 2; i <= day; i++) {
            if(i >= 8) {
                notSickPeople[i] = sickPeople[i - 7] + notSickPeople[i-1];
                sickPeople[i] = 3 * sickPeople[i-1] - notSickPeople[i];
                if(sickPeople[i] > 100_000) {
                    sickPeople[i] = 100_000;
                }
                if(notSickPeople[i] > 100_000) {
                    notSickPeople[i] = 100_000;
                }
                if(sickPeople[i] - notSickPeople[i] == 0) {
                    System.out.println("Epidemia zakończona, w dniu: " + i + ", ponieważ wszycy zyskali status ozdrowieńca");
                    break;
                }
//                System.out.println("sickPeople[" + i + "] = 3 * sickPeople[" + (i-1) + "] - notSickPeople[" + i + "]");
//                System.out.println(sickPeople[i] + " = 3 * " + sickPeople[i-1] + " - " + notSickPeople[i]);
            } else {
                sickPeople[i] = 3 * sickPeople[i-1];
            }
        }

        for(int i = 1; i <= day; i++) {
            System.out.print(sickPeople[i] + " ");
        }
        System.out.println();
        for(int i = 8; i <= day; i++) {
            System.out.print(notSickPeople[i] + " ");
        }
    }

    // when after 7 days people not sick
    private static long epidemicDivideAndConquer(int day) {
        if (day == 1) {
            sick[day] = 10;
            return 10; // number of sick people
        } else if (day <= 7) {
            sick[day] = (int) (10 * Math.pow(3, day - 1));
            return (int) (10 * Math.pow(3, day - 1));
        } else {
            notSick[day] = epidemicDivideAndConquer(day - 7) + notSick[day - 1];
            sick[day] = (epidemicDivideAndConquer(day - 1) * 3) - epidemicDivideAndConquer(day - 7);

            if (sick[day] > 100_000) {
                sick[day] = 100_000;
            }
            if (notSick[day] > 100_000) {
                notSick[day] = 100_000;
            }

            if(sick[day] - notSick[day] == 0) {
                return day;
            }
            return (epidemicDivideAndConquer(day - 1) * 3) - epidemicDivideAndConquer(day - 7);
        }
    }

    // when after 7 days people not sick but after 14 days they can be sick again
    private static long epidemicDivideAndConquerWith14Days(int day) {
        if (day == 1) {
            sick[day] = 10;
            return 10; // number of sick people
        } else if (day <= 7) {
            sick[day] = (int) (10 * Math.pow(3, day - 1));
            return (int) (10 * Math.pow(3, day - 1));
        } else if (day <= 21) {
            notSick[day] = epidemicDivideAndConquerWith14Days(day - 7) + notSick[day - 1];
            sick[day] = (epidemicDivideAndConquerWith14Days(day - 1) * 3) - notSick[day];
            if (sick[day] > 100_000) {
                sick[day] = 100_000;
            }
            if (notSick[day] > 100_000) {
                notSick[day] = 100_000;
            }
            return (epidemicDivideAndConquerWith14Days(day - 1) * 3) - epidemicDivideAndConquerWith14Days(day - 7);
        } else {
            notSick[day] =  notSick[day - 1] - epidemicDivideAndConquerWith14Days(day - 14);
            sick[day] = (epidemicDivideAndConquerWith14Days(day - 1) * 3) + notSick[day - 14];
            if (sick[day] > 100_000) {
                sick[day] = 100_000;
            }
            if (notSick[day] > 100_00) {
                notSick[day] = 100_000;
            }
            return (epidemicDivideAndConquerWith14Days(day - 1) * 3) + epidemicDivideAndConquerWith14Days(day - 14);
        }
    }

    //    private static int epidemic(int day) {
//        if (day == 1) {
//            tab[day] = 10;
//            return 10; // number of sick people
//        } else if (day <= 7) {
//            tab[day] = (int) (10 * Math.pow(3, day - 1));
//            return (int) (10 * Math.pow(3, day - 1));
//        } else if(day <= 14){
//            tab[day] = (epidemic(day - 1) * 3) - epidemic(day - 7);
//            return (epidemic(day - 1) * 3) - epidemic(day - 7);
//        }
//        tab[day] = (epidemic(day - 1) * 3) + epidemic(day - 14);
//        return (epidemic(day - 1) * 3) + epidemic(day - 14); // jesli liczbe zwqroic > 100000 to znaczy ze epiemia zabila wzsysstkihc xd
//    }
    // when after 7 days people are no sick
}
