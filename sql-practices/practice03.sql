-- 테이블간 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no, b.first_name,a.salary
	from salaries a, employees b
    where a.emp_no = b.emp_no
    and a.to_date = '9999-01-01'
    order by a.salary desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select a.emp_no, b.first_name,a.title
  from titles a, employees b
  where a.emp_no = b.emp_no
  and a.to_date = '9999-01-01'
  order by b.first_name;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select a.emp_no, concat(a.first_name,' ', a.last_name), b.salary
from employees a, salaries b, dept_emp c, (select a.dept_no as dept_no, avg(salary) as avg_salary
                                 from dept_emp a, salaries b
                                 where a.emp_no = b.emp_no
                                 and a.to_date = '9999-01-01'
                                 and b.to_date = '9999-01-01'
                                 group by a.dept_no) d
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and c.dept_no = d.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and b.salary > d.avg_salary;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no, concat(a.first_name,' ',a.last_name), d.name, c.dept_name
from employees a, dept_emp b, departments c, (select b.dept_no as dept_no, concat(a.first_name,' ',a.last_name) as name
                                    from employees a, dept_manager b
                                    where a.emp_no = b.emp_no
                                                and b.to_date = '9999-01-01') d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and c.dept_no = d.dept_no
and b.to_date='9999-01-01';
  
-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.
select a.emp_no, concat(a.first_name,a.last_name) as '이름'
  from employees a, titles b
  where a.emp_no = b.emp_no
  and b.title='Technique Leader'
  and b.to_date != '9999-01-01';

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select concat(a.first_name,' ',a.last_name) as '이름', c.dept_name, d.title
  from employees a, dept_emp b, departments c, titles d
  where a.emp_no = d.emp_no
  and a.emp_no = b.emp_no
  and b.dept_no = c.dept_no
  and a.last_name like 'S%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
-- 이름, 급여 출력
-- 급여가 큰 순서대로 출력
select concat(a.first_name,' ',a.last_name) as '이름', c.salary
  from employees a, titles b, salaries c
  where a.emp_no = b.emp_no
  and a.emp_no = c.emp_no
  and b.title='Engineer'
  and b.to_date = '9999-01-01'
  and c.to_date = '9999-01-01'
  order by c.salary desc;
  
-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 급여가 큰 출력하세요.(부서이름, 연봉)
select d.dept_name, sum(salary)
  from employees a, salaries b, dept_emp c, departments d
  where a.emp_no = b.emp_no
  and a.emp_no = c.emp_no
  and c.dept_no = d.dept_no
  and c.to_date = '9999-01-01'
  and b.to_date = '9999-01-01'
  group by c.dept_no
  order by b.salary desc;

-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.(직책이름, 연봉)
select c.title, avg(b.salary)
  from employees a, salaries b, titles c
  where a.emp_no = b.emp_no
  and a.emp_no = c.emp_no
  and c.to_date = '9999-01-01'
  and b.to_date = '9999-01-01' 
  group by c.title
  order by avg(b.salary) desc;
  
select d.dept_name, a.first_name, b.salary, e.first_name, e.salary
  from employees a, salaries b, dept_emp c, departments d,(select d.dept_no as dept_no, b.salary as salary, a.first_name as first_name
															  from employees a, salaries b, dept_emp c, dept_manager d
															  where a.emp_no = b.emp_no
															  and a.emp_no = c.emp_no
															  and a.emp_no = d.emp_no
															  and b.to_date = '9999-01-01'
															  and c.to_date = '9999-01-01'
															  and d.to_date = '9999-01-01') e
 where a.emp_no = b.emp_no
 and a.emp_no = c.emp_no
 and d.dept_no = c.dept_no
 and c.dept_no = e.dept_no
 and b.to_date = '9999-01-01'
 and c.to_date = '9999-01-01'
 and b.salary > e.salary;