"use strict";

// Class Definition



var KTAddUser = function () {
	// Private Variables
	var _wizardEl;
	var _formEl;
	var _wizard;
	var _avatar;
	var _validations = [];

	// Private Functions
	var _initWizard = function () {
		// Initialize form wizard
		_wizard = new KTWizard(_wizardEl, {
			startStep: 1, // initial active step number 시작페이지
			clickableSteps: true  // allow step clicking
		});

		// Validation before going to next page
		_wizard.on('beforeNext', function (wizard) {
			// 다음단계로 못가...
			_wizard.stop();

			// 유효성검사 폼
			var validator = _validations[wizard.getStep() - 1]; // get validator for currnt step
			validator.validate().then(function (status) {
		        if (status == 'Valid') {
					_wizard.goNext();
					KTUtil.scrollTop();
				} else {
					Swal.fire({
		                text: "필수 항목을 모두 입력해주세요.",
		                icon: "error",
		                buttonsStyling: false,
		                confirmButtonText: "확인",
						customClass: {
							confirmButton: "btn font-weight-bold btn-light"
						}
		            }).then(function() {
						KTUtil.scrollTop();
					});
				}
		    });
		});

		// Change Event
		_wizard.on('change', function (wizard) {
			KTUtil.scrollTop();
		});
	}

	var _initValidations = function () {
		// Init form validation rules. For more info check the FormValidation
		// plugin's official documentation:https://formvalidation.io/

		// Validation Rules For Step 1
		_validations.push(FormValidation.formValidation(
			_formEl,
			{
				fields: {					
					
					M_PASS: {
						validators: {
							notEmpty: {
								message: '비밀번호는 필수항목입니다.'
							},
							 stringLength: {
							      min:6,
							      max:6,
							      message: '최소, 최대 길이는 6자리 입니다.'
							     }					
							}
					},
					M_NAME: {
						validators: {
							notEmpty: {
								message: '성명은 필수항목입니다.'
							},
							stringLength: {
							      max:3,
							      message: '최대 길이는 5자리 입니다.'
							     }
						}
					},
					
					
					M_MOBILE_TEL: {
						validators: {
							notEmpty: {
								message: '핸드폰번호는 필수항목입니다.'
							},
							 stringLength: {
							      min:13,
							      max:13,
							      message: '최대 길이는 13자리 입니다.'
							     }
						}
					},
					email: {
						validators: {	
							notEmpty: {
								message: '이메일은 필수항목입니다.'
							},
							emailAddress: {
								message: '이메일 형식에 맞지 않습니다.'
							}
						}
						
					},
					
				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger(),
					bootstrap: new FormValidation.plugins.Bootstrap()
				}
			}
		));	
		_validations.push(FormValidation.formValidation(
				_formEl,
				{
					fields: {
						// Step 2
						communication: {
							validators: {
								choice: {
									min: 1,
									message: 'Please select at least 1 option'
								}
							}
						},
						language: {
							validators: {
								notEmpty: {
									message: 'Please select a language'
								}
							}
						},
						timezone: {
							validators: {
								notEmpty: {
									message: 'Please select a timezone'
								}
							}
						}
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
			));

			_validations.push(FormValidation.formValidation(
				_formEl,
				{
					fields: {
						address1: {
							validators: {
								notEmpty: {
									message: 'Address is required'
								}
							}
						},
						postcode: {
							validators: {
								notEmpty: {
									message: 'Postcode is required'
								}
							}
						},
						city: {
							validators: {
								notEmpty: {
									message: 'City is required'
								}
							}
						},
						state: {
							validators: {
								notEmpty: {
									message: 'state is required'
								}
							}
						},
						country: {
							validators: {
								notEmpty: {
									message: 'Country is required'
								}
							}
						},
					},
					plugins: {
						trigger: new FormValidation.plugins.Trigger(),
						bootstrap: new FormValidation.plugins.Bootstrap()
					}
				}
			));
		}

	var _initAvatar = function () {
		_avatar = new KTImageInput('kt_user_add_avatar');
	}

	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('kt_wizard');
			_formEl = KTUtil.getById('kt_form');

			_initWizard();
			_initValidations();
			_initAvatar();
		}
	};
}();

jQuery(document).ready(function () {
	KTAddUser.init();
});
