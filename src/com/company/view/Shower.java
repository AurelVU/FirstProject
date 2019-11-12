package com.company.view;

import com.company.domain.Employer;
import com.company.domain.JobApplication;
import com.company.domain.JobOffer;
import com.company.domain.User;
import com.company.service.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Shower {
    EmoloyerServices emoloyerServices = new EmoloyerServices();
    EmploymentServices employmentServices = new EmploymentServices();
    JobApplicationServices jobApplicationServices = new JobApplicationServices();
    JobOfferServices jobOfferServices = new JobOfferServices();
    UserServices userServices = new UserServices();

    Scanner in = new Scanner(System.in);

    public void UserPanel() {
        String userlogin = "";
        Long id = null;

        boolean registration = false;
        while (!registration) {
            System.out.println("1 - регистрация, 2 - попытка входа");
            int key = in.nextInt();
            if (key == 1) {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                if (userServices.registration(login, password))
                    System.out.println("Регистрация прошла успешно");
                else
                    System.out.println("Пользователь с данныи логином уже существует");
            }
            if (key == 2) {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                registration = userServices.authorization(login, password);
                if (registration) {
                    System.out.println("Авторизация пройдена успешно");
                    userlogin = login;
                    id = userServices.getIdByLogin(login);
                } else
                    System.out.println("Неверный логин или пароль");
            }
        }

        while (true) {
            System.out.println("1 - работа с заявками на работу, 2 - работа с предложениями работы, 3 - работа с трудоустройствами, 4 - редактирование аккаунта, 5 - выход");
            int key = in.nextInt();
            if (key == 2) {
                JobOfferPanel(id, true);
            }
            if (key == 1) {
                JobApplicationPanel(id, true);
            }
            if (key == 3) {
                EmploymentPanel(id);
            }
            if (key == 4) {
                System.out.println("Введите новый пароль");
                in.nextLine();
                String password = in.nextLine();
                System.out.println("Введите о компании");
                //in.nextLine();
                String aboutCompany = in.nextLine();
                System.out.println("Введите направление деятельности");
                //in.nextLine();
                String lineActivity = in.nextLine();
                userServices.change(id, userlogin, password, 0, aboutCompany, lineActivity);
            }
            if (key == 5)
                return;
        }
    }

    public void EmployerPanel() {
        String employerlogin = "";
        Long id = null;
        boolean registration = false;
        while (!registration) {
            System.out.println("1 - регистрация, 2 - попытка входа");
            int key = in.nextInt();
            if (key == 1) {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                if (emoloyerServices.registration(login, password))
                    System.out.println("Регистрация прошла успешно");
                else
                    System.out.println("Пользователь с данныи логином уже существует");
            }
            if (key == 2) {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                registration = emoloyerServices.authorization(login, password);
                if (registration) {
                    System.out.println("Авторизация пройдена успешно");
                    employerlogin = login;
                    id = emoloyerServices.getIdByLogin(login);
                } else
                    System.out.println("Неверный логин или пароль");
            }
        }

        while (true) {
            System.out.println("1 - работа с заявками на работу, 2 - работа с предложениями работы, 3 - работа с трудоустройствами, 4 - редактирование аккаунта, 5 - выход");
            int key = in.nextInt();
            if (key == 2) {
                JobOfferPanel(id, false);
            }
            if (key == 1) {
                JobApplicationPanel(id, false);
            }
            if (key == 3) {
                EmploymentPanel(id);
            }
            if (key == 4) {
                System.out.println("Введите новый пароль");
                in.nextLine();
                String password = in.nextLine();
                System.out.println("Введите о компании");
                String aboutCompany = in.nextLine();
                System.out.println("Введите направление деятельности");
                String lineActivity = in.nextLine();
                emoloyerServices.change(id, employerlogin, password, 0, aboutCompany, lineActivity);
            }
            if (key == 5)
                return;
        }
    }

    public void JobApplicationPanel(Long id, boolean isUser) {
        System.out.println("1 - Показать все, 2 - Показать с учетом параметров, 3 - Создать новое, 4 - Изменить, 5 - Удалить, 6 - выйти");
        int key = in.nextInt();
        if (key == 1) {
            List<JobApplication> jobApplications = jobApplicationServices.getAll();
            jobApplications.forEach(System.out::println);
        }
        if (key == 2) {
            System.out.println("Введите id пользователя");
            in.nextLine();
            User user = userServices.getById(in.nextLong());
            System.out.println("Введите минимальную желаемую дату старта работы");
            in.nextLine();
            Date desiredStartTimeMin = null;
            try {
                desiredStartTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите максимальную желаемую дату старта работы");
            Date desiredStartTimeMax = null;
            try {
                desiredStartTimeMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Введите минимальную желаемую дату окончания работы");
            Date desiredFinishTimeMin = null;
            try {
                desiredFinishTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            in.nextLine();
            System.out.println("Введите максимальную желаемую дату окончания работы");
            Date desiredFinishTimeMax = null;
            try {
                desiredFinishTimeMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите минимальную оплату");
            BigDecimal desiredWageMin = null;
            desiredWageMin = in.nextBigDecimal();
            System.out.println("Введите максимальную оплату");
            BigDecimal desiredWageMax = null;
            desiredWageMax = in.nextBigDecimal();
            System.out.println("Введите минимальную желаемую дату размещения объявления");
            in.nextLine();
            Date placementDateMin = null;
            try {
                placementDateMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите максимальную желаемую дату размещения объявления");

            Date placementDateMax = null;
            try {
                String date = in.nextLine();
                placementDateMax = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<JobApplication> jobApplications = jobApplicationServices.getByParams(user, desiredStartTimeMin, desiredStartTimeMax, desiredFinishTimeMin, desiredFinishTimeMax, desiredWageMin,
                    desiredWageMax, placementDateMin, placementDateMax);
            jobApplications.forEach(System.out::println);
        }
        if (key == 3) {
            if (isUser) {
                User user = userServices.getById(id);

                System.out.println("Введите желаемую дату старта работы");
                in.nextLine();
                Date desiredStartTime = null;
                try {
                    desiredStartTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Введите желаемую дату окончания работы");
                Date desiredFinishTime = null;
                try {
                    desiredFinishTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Введите желаемую оплату");
                BigDecimal desiredWage = null;
                desiredWage = in.nextBigDecimal();


                Date placementDate = new Date();

                System.out.println("Введите направление деятельности");
                in.nextLine();
                String typeServece = in.nextLine();

                jobApplicationServices.create(user, desiredStartTime, desiredFinishTime, desiredWage, placementDate, typeServece);
            } else {
                System.out.println("У вас недостаточно прав");
            }
        }
        if (key == 4) {
            System.out.println("Введите id записи");
            Long idjob = in.nextLong();

            User user = userServices.getById(id);

            System.out.println("Введите минимальную желаемую дату старта работы");
            Date desiredStartTime = null;
            try {
                desiredStartTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date desiredFinishTime = null;
            try {
                desiredFinishTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            BigDecimal desiredWage = null;
            desiredWage = in.nextBigDecimal();

            Date placementDate = new Date();

            System.out.println("Введите направление деятельности");
            in.nextLine();
            String typeServece = in.nextLine();

            jobApplicationServices.change(idjob, user, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                    typeServece);
        }
        if (key == 5) {

            System.out.println("Введите id записи");
            Long idjob = in.nextLong();
            jobApplicationServices.delete(idjob);
        }
        if (key == 6)
            return;
    }

    public void JobOfferPanel(Long id, boolean isUser) {
        System.out.println("1 - Показать все, 2 - Показать с учетом параметров,  3 - Создать новое, 4 - Изменить, 5 - Удалить, 6 - выйти");
        int key = in.nextInt();
        if (key == 1) {
            List<JobOffer> jobOffers = jobOfferServices.getAll();
            jobOffers.forEach(System.out::println);
        }
        if (key == 2) {
            Employer employer = emoloyerServices.getById(in.nextLong());
            System.out.println("Введите минимальную желаемую дату старта работы");
            Date desiredStartTimeMin = null;
            try {
                desiredStartTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите максимальную желаемую дату старта работы");
            Date desiredStartTimeMax = null;
            try {
                desiredStartTimeMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Введите минимальную желаемую дату завершения работы");
            Date desiredFinishTimeMin = null;
            try {
                String date = in.nextLine();
                desiredFinishTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите максимальную желаемую дату завершения работы");
            Date desiredFinishTimeMax = null;
            try {
                desiredFinishTimeMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            BigDecimal desiredWageMin = null;
            System.out.println("Введите минимальную желаемую оплату");
            desiredWageMin = in.nextBigDecimal();
            BigDecimal desiredWageMax = null;
            System.out.println("Введите максимальную желаемую оплату");
            desiredWageMax = in.nextBigDecimal();
            System.out.println("Введите минимальную желаемую дату размещения объявления");
            Date placementDateMin = null;
            try {
                placementDateMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date placementDateMax = null;
            System.out.println("Введите максимальную желаемую дату размещения объявления");
            try {
                placementDateMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<JobOffer> jobOffers = jobOfferServices.getByParams(employer, desiredStartTimeMin, desiredStartTimeMax, desiredFinishTimeMin, desiredFinishTimeMax, desiredWageMin,
                    desiredWageMax, placementDateMin, placementDateMax);
            jobOffers.forEach(System.out::println);
        }
        if (key == 3) {
            if (!isUser) {
                Employer employer = emoloyerServices.getById(id);

                System.out.println("Введите желаемую дату старта работы");
                in.nextLine();
                Date desiredStartTime = null;
                try {
                    desiredStartTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Введите желаемую дату окончания работы");
                Date desiredFinishTime = null;
                try {
                    desiredFinishTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Введите желаемую оплату");
                BigDecimal desiredWage = null;
                desiredWage = in.nextBigDecimal();

                Date placementDate = new Date();

                System.out.println("Введите направление деятельности");

                String typeServece = in.nextLine();

                System.out.println("Введите прочее");

                String other = in.nextLine();

                jobOfferServices.create(employer, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                        typeServece, other);
            } else {
                System.out.println("У вас недостаточно прав");
            }
        }
        if (key == 4) {
            System.out.println("Введите id записи");
            Long idjob = in.nextLong();

            Employer employer = emoloyerServices.getById(id);

            System.out.println("Введите желаемую дату старта работы");
            Date desiredStartTime = null;
            try {
                desiredStartTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Введите желаемую дату окончания работы");
            Date desiredFinishTime = null;
            try {
                desiredFinishTime = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Введите желаемую оплату");
            BigDecimal desiredWage = null;
            desiredWage = in.nextBigDecimal();

            Date placementDate = new Date();

            System.out.println("Введите направление деятельности");
            in.nextLine();
            in.nextLine();
            String typeServece = in.nextLine();


            System.out.println("Введите прочее");
            in.nextLine();
            String other = in.nextLine();

            jobOfferServices.change(idjob, employer, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                    typeServece, other);
        }
        if (key == 5) {

            System.out.println("Введите id записи");
            Long idjob = in.nextLong();
            jobOfferServices.delete(idjob);
        }
        if (key == 6)
            return;
    }

    public void EmploymentPanel(Long id) {
        System.out.println("1 - Показать все, 2 - Показать с учетом параметров, 3 - Создать новое, 4 - Изменить, 5 - Удалить, 6 - выйти");
        int key = in.nextInt();
        if (key == 1) {
            employmentServices.getAll().forEach(System.out::println);
        }
        if (key == 2) {
            System.out.println("Введите id заявки на работу");
            JobApplication jobApplication = jobApplicationServices.getById(in.nextLong());
            System.out.println("Введите id предложения работы");
            JobOffer jobOffer = jobOfferServices.getById(in.nextLong());
            System.out.println("Введите id пользователя");
            User user = userServices.getById(in.nextLong());
            System.out.println("Введите id работодателя");
            Employer employer = emoloyerServices.getById(in.nextLong());
            System.out.println("Введите отзыв сотрудника");
            String employeeReview = in.nextLine();
            System.out.println("Введите отзыв компании");
            String companyReview = in.nextLine();
            System.out.println("Введите дату начала работы");
            Date startDateMin = null;
            try {
                startDateMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите дату начала работы");
            Date startDateMax = null;
            try {
                startDateMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите дату окончания работы");
            Date finishDateMin = null;
            try {
                finishDateMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите дату окончания работы");
            Date finishDateMax = null;
            try {
                finishDateMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            employmentServices.getByParams(jobApplication, jobOffer, user, employer, startDateMin, startDateMax,
                    finishDateMin, finishDateMax).forEach(System.out::println);
        }
        if (key == 3) {
            System.out.println("Введите id заявки на работу");
            JobApplication jobApplication = jobApplicationServices.getById(in.nextLong());
            System.out.println("Введите id предложения работы");
            JobOffer jobOffer = jobOfferServices.getById(in.nextLong());
            System.out.println("Введите id пользователя");
            User user = userServices.getById(in.nextLong());
            System.out.println("Введите id работодателя");
            Employer employer = emoloyerServices.getById(in.nextLong());
            System.out.println("Введите отзыв сотрудника");
            String employeeReview = in.nextLine();
            System.out.println("Введите отзыв компании");
            String companyReview = in.nextLine();
            System.out.println("Введите дату начала работы");
            Date startDate = null;
            try {
                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите дату окончания работы");
            Date finishDate = null;
            try {
                finishDate = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            employmentServices.create(jobApplication, jobOffer, user, employer, employeeReview, companyReview,
                    startDate, finishDate);
        }
        if (key == 4) {
            System.out.println("Введите id трудоустройства");
            Long idtr = in.nextLong();
            System.out.println("Введите id заявки на работу");
            JobApplication jobApplication = jobApplicationServices.getById(in.nextLong());
            System.out.println("Введите id предложения работы");
            JobOffer jobOffer = jobOfferServices.getById(in.nextLong());
            System.out.println("Введите id пользователя");
            User user = userServices.getById(in.nextLong());
            System.out.println("Введите id работодателя");
            Employer employer = emoloyerServices.getById(in.nextLong());
            System.out.println("Введите отзыв сотрудника");
            String employeeReview = in.nextLine();
            System.out.println("Введите отзыв компании");
            String companyReview = in.nextLine();
            System.out.println("Введите дату начала работы");
            Date startDate = null;
            try {
                startDate = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Введите дату окончания работы");
            Date finishDate = null;
            try {
                finishDate = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            employmentServices.change(idtr, jobApplication, jobOffer, user, employer, employeeReview, companyReview,
                    startDate, finishDate);
        }
        if (key == 5) {
            System.out.println("Введите id");
            employmentServices.delete(in.nextLong());
        }
        if (key == 6)
            return;
    }
}
