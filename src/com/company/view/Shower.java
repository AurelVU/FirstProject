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

    public void UserPanel()
    {
        String userlogin = "";
        Long id = null;

        boolean registration = false;
        while (!registration)
        {
            System.out.println("1 - регистрация, 2 - попытка входа");
            int key = in.nextInt();
            if (key == 1)
            {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                if (userServices.registration(login, password))
                    System.out.println("Регистрация прошла успешно");
                else
                    System.out.println("Пользователь с данныи логином уже существует");
            }
            if (key == 2)
            {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                registration = userServices.authorization(login, password);
                if (registration) {
                    System.out.println("Авторизация пройдена успешно");
                    userlogin = login;
                    id = userServices.getIdByLogin(login);
                }
                else
                    System.out.println("Неверный логин или пароль");
            }
        }

        while (true)
        {
            System.out.println("1 - работа с заявками на работу, 2 - работа с предложениями работы, 3 - работа с трудоустройствами, 4 - редактирование аккаунта");
            int key = in.nextInt();
            if (key == 1)
            {
                JobOfferPanel(id);
            }
            if (key == 2)
            {
                JobApplicationPanel(id);
            }
            if (key == 3)
            {
                EmploymentPanel(id);
            }
            if (key == 4)
            {
                System.out.println("Введите новый пароль");
                in.nextLine();
                String password = in.nextLine();;
                System.out.println("Введите о компании");
                in.nextLine();
                String aboutCompany = in.nextLine();;
                System.out.println("Введите направление деятельности");
                in.nextLine();
                String lineActivity = in.nextLine();;
                emoloyerServices.change(id, userlogin, password, 0, aboutCompany, lineActivity);
            }
        }
    }

    public void EmployerPanel()
    {
        String employerlogin = "";
        Long id = null;
        boolean registration = false;
        while (!registration)
        {
            System.out.println("1 - регистрация, 2 - попытка входа");
            int key = in.nextInt();
            if (key == 1)
            {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                if (emoloyerServices.registration(login, password))
                    System.out.println("Регистрация прошла успешно");
                else
                    System.out.println("Пользователь с данныи логином уже существует");
            }
            if (key == 2)
            {
                System.out.println("Введите логин, затем пароль");
                in.nextLine();
                String login = in.nextLine();
                String password = in.nextLine();
                registration = emoloyerServices.authorization(login, password);
                if (registration) {
                    System.out.println("Авторизация пройдена успешно");
                    employerlogin = login;
                    id = emoloyerServices.getIdByLogin(login);
                }
                else
                    System.out.println("Неверный логин или пароль");
            }
        }

        while (true)
        {
            System.out.println("1 - работа с заявками на работу, 2 - работа с предложениями работы, 3 - работа с трудоустройствами, 4 - редактирование аккаунта");
            int key = in.nextInt();
            if (key == 1)
            {
                JobOfferPanel(id);
            }
            if (key == 2)
            {
                JobApplicationPanel(id);
            }
            if (key == 3)
            {
                EmploymentPanel(id);
            }
            if (key == 4)
            {

                System.out.println("Введите новый пароль");
                in.nextLine();
                String password = in.nextLine();;
                System.out.println("Введите о компании");
                in.nextLine();
                String aboutCompany = in.nextLine();;
                System.out.println("Введите направление деятельности");
                in.nextLine();
                String lineActivity = in.nextLine();;
                emoloyerServices.change(id, employerlogin, password, 0, aboutCompany, lineActivity);
            }
        }
    }

    public void JobApplicationPanel(Long id)
    {
        System.out.println("1 - Показать все, 2 - Показать с учетом параметров, 3 - Создать новое, 4 - Изменить, 5 - Удалить, 6 - выйти");
        int key = in.nextInt();
        if (key == 1)
        {
            List<JobApplication> jobApplications = jobApplicationServices.getAll();
            jobApplications.forEach(System.out::println);
        }
        if (key == 2)
        {
            System.out.println("Введите id пользователя");
            User user = userServices.getById(in.nextLong());
            System.out.println("Введите минимальную желаемую дату старта работы");
            Date desiredStartTimeMin = null;
            try {
                desiredStartTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date desiredStartTimeMax = null;
            try {
                desiredStartTimeMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date desiredFinishTimeMin = null;
            try {
                desiredFinishTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date desiredFinishTimeMax = null;
            try {
                desiredFinishTimeMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            BigDecimal desiredWageMin = null;
            desiredWageMin = in.nextBigDecimal();
            BigDecimal desiredWageMax = null;
            desiredWageMax = in.nextBigDecimal();
            Date placementDateMin = null;
            try {
                placementDateMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date placementDateMax = null;
            try {
                placementDateMax = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<JobApplication> jobApplications = jobApplicationServices.getByParams(user, desiredStartTimeMin, desiredStartTimeMax, desiredFinishTimeMin, desiredFinishTimeMax, desiredWageMin,
                    desiredWageMax, placementDateMin, placementDateMax);
        }
        if (key == 3)
        {
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

            jobApplicationServices.create(user, desiredStartTime, desiredFinishTime, desiredWage, placementDate, typeServece);
        }
        if (key == 4)
        {
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
        if (key == 5)
        {

            System.out.println("Введите id записи");
            Long idjob = in.nextLong();
            jobApplicationServices.delete(idjob);
        }
        if (key == 6)
            return;
    }

    public void JobOfferPanel(Long id)
    {
        System.out.println("1 - Показать все, 2 - Показать с учетом параметров,  3 - Создать новое, 4 - Изменить, 5 - Удалить, 6 - выйти");
        int key = in.nextInt();
        if (key == 1)
        {
            List<JobOffer> jobOffers = jobOfferServices.getAll();
            jobOffers.forEach(System.out::println);
        }
        if (key == 3)
        {
            System.out.println("Введите id пользователя");
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
                desiredFinishTimeMin = new SimpleDateFormat("dd/MM/yyyy").parse(in.nextLine());
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
        }
        if (key == 2)
        {
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
            String typeServece = in.nextLine();

            System.out.println("Введите прочее");
            in.nextLine();
            String other = in.nextLine();

            jobOfferServices.create(employer, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                    typeServece, other);
        }
        if (key == 4)
        {
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
            String typeServece = in.nextLine();


            System.out.println("Введите прочее");
            in.nextLine();
            String other = in.nextLine();

            jobOfferServices.change(idjob, employer, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                    typeServece, other);
        }
        if (key == 5)
        {

            System.out.println("Введите id записи");
            Long idjob = in.nextLong();
            jobOfferServices.delete(idjob);
        }
        if (key == 6)
            return;
    }

    public void EmploymentPanel(Long id)
    {
        System.out.println("1 - Показать все, 2 - Показать с учетом параметров, 3 - Создать новое, 4 - Изменить, 5 - Удалить, 6 - выйти");
        int key = in.nextInt();
        if (key == 1)
        {}
        if (key == 2)
        {}
        if (key == 3)
        {}
        if (key == 4)
        {}
        if (key == 5)
        {}
        if (key == 6)
            return;
    }
}
