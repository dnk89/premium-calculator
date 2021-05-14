# premium-calculator library

Library divided to three main java packages:
* *com.theinsurancecompany.premiumcalc.businesslogic* - contains public PremiumCalculator interface and all implementation details in subpackage *impl*
* *com.theinsurancecompany.premiumcalc.config* - contains Spring IoC container configuration to properly instantiate PremiumCalculator
* *com.theinsurancecompany.premiumcalc.domain* - contains enumerations and immutable entity objects which describes insurance policy structure

## Implementation
In Spring context configuration PremiumCalculator primary implementation is TotalPremiumCalculator class which main logic 
is looping through all insurance risk types, invoking concrete risk type premium calculators and summarizes all those results in one.

To obtain concrete risk premium calculators DefaultConcreteRiskPremiumCalculatorFactory is used. 

Each concrete risk type calculator is based on abstract ConcreteRiskPremiumCalculatorBase which 
implements *calculate* method as pattern method, based on premium calculation formula from specs:
* Total sum insured of all policy's sub-objects with concrete type multiplied by coefficient

Concrete risk type calculators should call base constructor with concrete type and implement coefficient calculation algorithm. 
Steps to extend library with new risk type:
1. Add new value to RiskType enum
2. Implement new concrete risk premium calculator for new risk type
3. Add it to DefaultConcreteRiskPremiumCalculatorFactory calculators map

If new concrete risk premium calculator will not be implemented and added to factory, 
then TotalPremiumCalculator will throw ConcreteRiskPremiumCalculatorNotFoundException on calculate method invocation.

Policy object entity and all sub-entities are immutable and corresponding Builder classes should be used instead of constructors
 which declared as private for that reason:
* Policy.Builder
* PolicyObject.Builder
* PolicyObjectItem.Builder

Each builder implements validation logic before instance is created. 
If something is wrong, PolicyValidationException will be thrown. For example, policy with zero objects is not allowed and in that case validation exception will be thrown.

## Tests
Solution contains two unit tests based on acceptance criteria from specs:
* *policy_with_one_object_and_two_sub_objects_criteria* - examines if calculation result is correct when one policy object is included in policy
* *policy_with_total_sums_criteria* - examines if calculation result is correct when many policy objects are included in policy